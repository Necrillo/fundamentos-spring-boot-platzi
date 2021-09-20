package com.fundamientosplatzi.springboot.fundamentos;

import com.fundamientosplatzi.springboot.fundamentos.bean.*;
import com.fundamientosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamientosplatzi.springboot.fundamentos.entity.User;
import com.fundamientosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamientosplatzi.springboot.fundamentos.repository.UserRepository;
import com.fundamientosplatzi.springboot.fundamentos.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;


	public FundamentosApplication(@Qualifier("componentTwoImplements") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository, UserService userService){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//ejemplosAnteriores();
		saveUsersInDatabase();
		//getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}

	private void saveWithErrorTransactional(){
		User test1 = new User("TestTransactional1","TestTransactional1@domain.com", LocalDate.now());
		User test2 = new User("TestTransactional2","TestTransactional2@domain.com", LocalDate.now());
		User test3 = new User("TestTransactional3","TestTransactional1@domain.com", LocalDate.now());
		User test4 = new User("TestTransactional4","TestTransactional4@domain.com", LocalDate.now());

		List<User> users = Arrays.asList(test1,test2, test3, test4);
		try {
			userService.saveTransactional(users);
		}catch (Exception e){
			LOGGER.error("Este es una exception dentro del metodo transaccional: "+ e);
		}
		userService.getAllUsers().stream()
				.forEach(user -> LOGGER.info("Este es el usuario dentro del metodo transaccional "+user));
	}

	private void getInformationJpqlFromUser() {
		/*LOGGER.info("Usuario con finByUserEmail :"+
				userRepository.findByUserEmail("jhon@domain.com")
				.orElseThrow(()->new RuntimeException("No se encontro el usuario")));

		userRepository.findAndSort("user", Sort.by("id").descending())
				.stream()
				.forEach(user -> LOGGER.info("user with method sort "+ user));

		userRepository.findByName("Mario")
				.stream().
				forEach(user->LOGGER.info("Usuario con query method findByName" + user));

		LOGGER.info("Usuario con query method findByEmailAndName" + userRepository.findByEmailAndName("user2@domain.com","Mario")
				.orElseThrow(()->new RuntimeException("Usuario no encontrado")));

		userRepository.findByNameLike("%M%").stream().forEach(user -> LOGGER.info("usuario findbynamelike "+ user));

		userRepository.findByNameOrEmail("user7",null).stream().forEach(user -> LOGGER.info("usuario findByNameOrEmail "+ user));*/

		//userRepository.findByBirthDateBetween(LocalDate.of(2021,03,23),LocalDate.of(2021,03,24)).stream().forEach(user -> LOGGER.info("Usuario findByBirthDateBetween "+ user));
		/*userRepository.findByNameLikeOrderByIdDesc("%user%")
				.stream()
				.forEach(user -> LOGGER.info("Usuario encontrado con findByNameLikeOrOrderByIdDesc"+user));	*/

		/*userRepository.findByNameContainingOrderByIdDesc("M")
				.stream()
				.forEach(user -> LOGGER.info("Usuario encontrado con findByNameLikeOrOrderByIdDesc"+user));*/

		/* LOGGER.info("El usuario a partir del named parameter es: "+
		userRepository.getAllByBirthDateAndEmail( LocalDate.of(2021,03,21),"user2@domain.com")
				.orElseThrow(()->new RuntimeException("No se encontro el usuario a partir del named parameter")));*/

	}

	private void saveUsersInDatabase(){
		User user1 = new User("John","jhon@domain.com", LocalDate.of(2021,03,20));
		User user2 = new User("Mario","user2@domain.com", LocalDate.of(2021,03,21));
		User user3 = new User("John","user3@domain.com", LocalDate.of(2021,03,22));
		User user4 = new User("John","user4@domain.com", LocalDate.of(2021,03,23));
		User user5 = new User("John","user5@domain.com", LocalDate.of(2021,03,24));
		User user6 = new User("Michael","user6@domain.com", LocalDate.of(2021,03,25));
		User user7 = new User("user7","user7@domain.com", LocalDate.of(2021,03,26));
		User user8 = new User("user8","user8@domain.com", LocalDate.of(2021,03,27));
		User user9 = new User("user9","user9@domain.com", LocalDate.of(2021,03,28));
		User user10 = new User("user10","user10@domain.com", LocalDate.of(2021,03,29));
		List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10);
		list.stream().forEach(userRepository::save);
	}

	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + " - "+ userPojo.getPassword()+ "-" + userPojo.getAge());
		try {
			//error
			int value = 10/0;
			LOGGER.debug("Mi valor es:"+value);
		}catch (Exception e){
			LOGGER.error("Esto es un error del aplicativo :"+e.getMessage());
		}

	}
}
