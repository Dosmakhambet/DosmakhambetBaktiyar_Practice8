package com.dosmakhambetbbaktiyar_practice8;

import com.dosmakhambetbbaktiyar_practice8.config.SecurityConfig;
import com.dosmakhambetbbaktiyar_practice8.model.*;
import com.dosmakhambetbbaktiyar_practice8.repository.UserRepository;
import com.dosmakhambetbbaktiyar_practice8.service.UserService;
import com.dosmakhambetbbaktiyar_practice8.service.impl.UserServiceImpl;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    private User getUser1(){
        return User.builder()
                .id(1L)
                .userName("test")
                .fullName("Test Testov")
                .role(Role.USER)
                .status(Status.ACTIVE)
                .build();
    }

    private User getUser2(){
        return User.builder()
                .id(2L)
                .userName("admin")
                .fullName("Admin admin")
                .role(Role.ADMIN)
                .status(Status.ACTIVE)
                .events(Set.of(getEvent1()))
                .build();
    }

    private User getUser3(){
        return User.builder()
                .id(5L)
                .userName("Moderator")
                .fullName("Moderator Moderator")
                .role(Role.MODERATOR)
                .status(Status.ACTIVE)
                .events(Set.of(getEvent2(),getEvent3()))
                .build();
    }

    private Event getEvent1(){
        return  Event.builder()
                .id(1L)
                .file(getFile1())
                .build();
    }

    private Event getEvent2(){
        return Event.builder()
                .id(2L)
                .file(getFile2())
                .build();
    }

    private Event getEvent3(){
        return Event.builder()
                .id(5L)
                .file(getFile3())
                .build();
    }

    private File getFile1(){
        return new File(1L,"file",Status.ACTIVE);
    }

    private File getFile2(){
        return new File(3L,"file2",Status.ACTIVE);
    }

    private File getFile3(){
        return new File(9L,"file5",Status.ACTIVE);
    }

    private List<User> getUsers(){
        return Lists.list(getUser1(), getUser2(), getUser3());
    }

    @Test
    public void get(){
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(getUser3()));

        User user = service.findById(2L);

        assertEquals(5,user.getId().intValue());
        assertEquals(getEvent2().getFile().getLocation(),user.getEvents().stream().iterator().next().getFile().getLocation());
        assertEquals("Moderator",user.getUserName());
        assertEquals("Moderator Moderator",user.getFullName());
        assertEquals(2,user.getEvents().size());
        assertEquals(Status.ACTIVE,user.getStatus());
    }

    @Test
    public void getAll(){
        when(repository.findAll()).thenReturn(getUsers());

        List<User> users = service.findAll();

        assertEquals(3,users.size());
        assertNull(users.get(0).getEvents());
    }

}
