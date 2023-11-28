package net.javaguides.springboot.entity;

import static org.junit.jupiter.api.Assertions.*;




import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class UserTest {
	
	
	@InjectMocks 
	User user;

	@Test
	void testGetId() {
		user.getId();
		}

	@Test
	void testGetFirstName() {
		user.getFirstName();
	}

	@Test
	void testGetLastName() {
		user.getLastName();	}

	@Test
	void testGetEmail() {
		user.getEmail();
	}

	@Test
	void testSetId() {
		user.setId(1L);;
	}

	@Test
	void testSetFirstName() {
		user.setFirstName("manasa");
	}

	@Test
	void testSetLastName() {
		user.setLastName("bv");
	}

	@Test
	void testSetEmail() {
		user.setEmail("manasabv139@gmail.com");
	}

	

}
