package com.estate.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estate.constant.ConfigurationConstant;
import com.estate.converter.UserConverter;
import com.estate.core.entity.ConfigurationEntity;
import com.estate.core.entity.RoleEntity;
import com.estate.core.entity.UserEntity;
import com.estate.core.repository.ConfigurationRepository;
import com.estate.core.repository.RoleRepository;
import com.estate.core.repository.UserRepository;
import com.estate.dto.MailDTO;
import com.estate.dto.UserDTO;
import com.estate.service.IUserService;
import com.estate.service.MailService;
import com.estate.utils.StringGenerate;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private MailService mailService;

	@Autowired
	private ConfigurationRepository configurationRepository;

	@Override
	public UserDTO findOneByUserName(String userName) {
		UserEntity userEntity = userRepository.findOneByUserName(userName);
		UserDTO userDTO = userConverter.convertToDto(userEntity);
		return userDTO;
	}

	@Override
	public List<UserDTO> getUsers(String searchValue, Pageable pageable) {
		Page<UserEntity> newsPage = null;
		if (searchValue != null) {
			newsPage = userRepository.findByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(searchValue, searchValue, 0, pageable);
		} else {
			newsPage = userRepository.findByStatusNot(0, pageable);
		}
		List<UserEntity> newsEntities = newsPage.getContent();
		List<UserDTO> result = new ArrayList<UserDTO>();
		for (UserEntity userEntity : newsEntities) {
			UserDTO userDTO = userConverter.convertToDto(userEntity);
			result.add(userDTO);
		}
		return result;
	}

	@Override
	public int getTotalItems(String searchValue) {
		int totalItem = 0;
		if (searchValue != null) {
			totalItem = (int) userRepository.countByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(searchValue, searchValue, 0);
		} else {
			totalItem = (int) userRepository.countByStatusNot(0);
		}
		return totalItem;
	}

	@Override
	@Transactional
	public UserDTO insert(UserDTO newUser) {
		RoleEntity role = roleRepository.findOneByCode(newUser.getRoleCode());
		List<RoleEntity> roles = new ArrayList<>();
		roles.add(role);
		UserEntity userEntity = userConverter.convertToEntity(newUser);
		userEntity.setRoles(roles);
		userEntity.setStatus(1);
		String passwordGenerate = StringGenerate.generateValue(8);
		userEntity.setPassword(passwordEncoder.encode(passwordGenerate));
		sentMailToUser(userEntity, passwordGenerate);
		return userConverter.convertToDto(userRepository.save(userEntity));
	}

	private void sentMailToUser(UserEntity user, String pass) {
		MailDTO mail = new MailDTO();
		mail.setMailFrom("ngocchienforwork@gmail.com");
		String[] mailTo = new String[]{user.getEmail()};
		mail.setMailTo(mailTo);
		Map<String,Object > model = new HashMap<>();
		model.put("username", user.getUserName());
		model.put("password", pass);
		mail.setModel(model);

		ConfigurationEntity configuration = configurationRepository.findOneByKey(ConfigurationConstant.TEMPLATE_USER_CREATE);
		mail.setTemplate(configuration.getValue());
		mailService.sendEmail(mail);
	}

	@Override
	public UserDTO findUserById(long id) {
		UserEntity entity = userRepository.findOne(id);
		List<RoleEntity> roles = entity.getRoles();
		UserDTO dto = userConverter.convertToDto(entity);
		roles.forEach(item ->{
			dto.setRoleCode(item.getCode());
		});
		return dto;
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (Long item: ids) {
			UserEntity userEntity = userRepository.findOne(item);
			userEntity.setStatus(0);
			userRepository.save(userEntity);
		}
	}

	@Override
	@Transactional
	public UserDTO update(Long id, UserDTO updateUser) {
		RoleEntity role = roleRepository.findOneByCode(updateUser.getRoleCode());
		List<RoleEntity> roles = new ArrayList<>();
		roles.add(role);
		UserEntity oldUser = userRepository.findOne(id);
		oldUser.setEmail(updateUser.getEmail());
		oldUser.setFullName(updateUser.getFullName());
		oldUser.setPassword(updateUser.getPassword());
		oldUser.setPhoneNumber(updateUser.getPhoneNumber());
		oldUser.setStatus(1);
		oldUser.setRoles(roles);
		return userConverter.convertToDto(userRepository.save(oldUser));
	}
}
