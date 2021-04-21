package com.springboot.subway;

import com.springboot.subway.model.subway.station;
import com.springboot.subway.model.user.User;
import com.springboot.subway.model.user.role;
import com.springboot.subway.repository.RoleRepository;
import com.springboot.subway.repository.StationRepository;
import com.springboot.subway.repository.UserRepository;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SubwayReservationSystemApplication {

    public static void main(String[] args) {
        System.out.println("When you run this project, it will auto init some data insert into my mongodb DB.");
        SpringApplication.run(SubwayReservationSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StationRepository stationRepository, RoleRepository roleRepository, UserRepository userRepository
    ) {

        return args -> {

            /* init our roles */
            role adminRole = roleRepository.findByRole("ADMIN");
            if (adminRole == null) {
                adminRole = role.builder().role("ADMIN").build();
                roleRepository.save(adminRole);
            }

            Optional<role> r = Optional.ofNullable(roleRepository.findByRole("TRAVELLER"));
            if (!r.isPresent()) {
                roleRepository.save(role.builder().role("TRAVELLER").build());
            }


            /* Create my five railway stations */

            Optional<station> sf = Optional.ofNullable(stationRepository.findByCode("SF"));
            if (!sf.isPresent()) {
                stationRepository.save(
                        station.builder()
                                .name("San Francisco")
                                .detail("near SF airport")
                                .code("SF")
                                .build()
                );
            }

            Optional<station> sm = Optional.ofNullable(stationRepository.findByCode("SM"));
            if (!sm.isPresent()) {
                stationRepository.save(
                        station.builder()
                                .name("San Mateo")
                                .detail("near San Mateo")
                                .code("SM")
                                .build()
                );
            }

            Optional<station> rw = Optional.ofNullable(stationRepository.findByCode("RW"));
            if (!rw.isPresent()) {
                stationRepository.save(
                        station.builder()
                                .name("Red Wood")
                                .detail("near Facebook")
                                .code("RW")
                                .build()
                );
            }

            Optional<station> pa = Optional.ofNullable(stationRepository.findByCode("PA"));
            if (!pa.isPresent()) {
                stationRepository.save(
                        station.builder()
                                .name("Palo Alto")
                                .detail("near iKEA")
                                .code("PA")
                                .build()
                );
            }

            Optional<station> sj = Optional.ofNullable(stationRepository.findByCode("SJ"));
            if (!sj.isPresent()) {
                stationRepository.save(
                        station.builder()
                                .name("San Jose")
                                .detail("near Berryessa")
                                .code("SJ")
                                .build()
                );
            }


            /* create admin user */

            Optional<User> check = Optional.ofNullable(userRepository.findByEmail("admin.user@gmail.com"));
            if (!check.isPresent()) {
                userRepository.save(
                        User.builder()
                                .email("admin.user@gmail.com")
                                .firstName("Tim")
                                .lastName("cook")
                                .mobileNumber("4082222222")
                                .password("123456")
                                .roles(new HashSet<>(Arrays.asList(adminRole)))
                                .build()
                );
            }
        };
    }
}
