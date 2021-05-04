package com.springboot.subway;

import com.springboot.subway.model.subway.Agency;
import com.springboot.subway.model.subway.Train;
import com.springboot.subway.model.subway.Trip;
import com.springboot.subway.model.subway.station;
import com.springboot.subway.model.user.User;
import com.springboot.subway.model.user.role;
import com.springboot.subway.repository.AgencyRepository;
import com.springboot.subway.repository.RoleRepository;
import com.springboot.subway.repository.StationRepository;
import com.springboot.subway.repository.TrainRepository;
import com.springboot.subway.repository.TripRepository;
import com.springboot.subway.repository.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SubwayReservationSystemApplication {

    public static void main(String[] args) {
        System.out.println("When you run this project, it will auto init some data insert into my mongodb DB. Only Once!");
        SpringApplication.run(SubwayReservationSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StationRepository stationRepository, RoleRepository roleRepository, UserRepository userRepository,
                           AgencyRepository agencyRepository, TrainRepository trainRepository, TripRepository tripRepository
    ) {

        return args -> {

            /* init our roles */

            role adminRole = (!Optional.ofNullable(roleRepository.findByRole("ADMIN")).isPresent())
                    ?
                   roleRepository.save(role.builder().role("ADMIN").build())
                    :
                    roleRepository.findByRole("ADMIN");



            role traveller = (! Optional.ofNullable(roleRepository.findByRole("TRAVELLER")).isPresent()) ?
                    roleRepository.save(role.builder().role("TRAVELLER").build()) : roleRepository.findByRole("TRAVELLER");



            /* Create my five railway stations */

            station sf = (!Optional.ofNullable(stationRepository.findByCode("SF")).isPresent()) ?
                    stationRepository.save(
                            station.builder()
                                    .name("San Francisco")
                                    .detail("near SF airport")
                                    .code("SF")
                                    .build()
                    ) :stationRepository.findByCode("SF");



            station sm = (!Optional.ofNullable(stationRepository.findByCode("SM")).isPresent()) ?
                    stationRepository.save(
                            station.builder()
                                    .name("San Mateo")
                                    .detail("near San Mateo")
                                    .code("SM")
                                    .build()
                    ) :stationRepository.findByCode("SM");


            station rw = (!Optional.ofNullable(stationRepository.findByCode("RW")).isPresent()) ?
                    stationRepository.save(
                            station.builder()
                                    .name("Red Wood")
                                    .detail("near Facebook")
                                    .code("RW")
                                    .build()
                    ) : stationRepository.findByCode("RW");


            station pa = (!Optional.ofNullable(stationRepository.findByCode("PA")).isPresent()) ?
                    stationRepository.save(
                            station.builder()
                                    .name("Palo Alto")
                                    .detail("near iKEA")
                                    .code("PA")
                                    .build()
                    ) : stationRepository.findByCode("PA");


            station sj = (!Optional.ofNullable(stationRepository.findByCode("SJ")).isPresent()) ?
                    stationRepository.save(
                            station.builder()
                                    .name("San Jose")
                                    .detail("near Berryessa")
                                    .code("SJ")
                                    .build()
                    ) : stationRepository.findByCode("SJ");



            /* create admin user */

            User admin = (!Optional.ofNullable(userRepository.findByEmail("admin.user@gmail.com")).isPresent()) ?
                    userRepository.save(
                            User.builder()
                                    .email("admin.user@gmail.com")
                                    .firstName("Tim")
                                    .lastName("cook")
                                    .phoneNumber("4082222222")
                                    .password("123456")
                                    .roles(new ArrayList<>(Arrays.asList(adminRole)))
                                    .build()
                    ) : userRepository.findByEmail("admin.user@gmail.com");


            /* init a agency */
            Agency tom = (!Optional.ofNullable(agencyRepository.findByCode("AGENCY-TOM")).isPresent()) ?
                    agencyRepository.save(
                            Agency.builder()
                            .code("AGENCY-TOM")
                            .name("Tom Agency")
                            .details("near the moon")
                            .user(admin)
                            .build()
                    ) : agencyRepository.findByCode("AGENCY-TOM");

            /* init a train */
            Train solarEnergy = (!Optional.ofNullable(trainRepository.findByCode("AGENCY-TIM")).isPresent()) ?
                    trainRepository.save(
                            Train.builder()
                            .code("AGENCY-TIM")
                            .agency(tom)
                            .capacity(100)
                            .build()
                    ) : trainRepository.findByCode("AGENCY-TIM");


            /* Adding my train into an agency */
            if (tom.getTrains() == null) {
                tom.setTrains(new ArrayList<>());
                tom.getTrains().add(solarEnergy);
            }


            /* init a trip (for a ticket) */
            Trip trip = (!Optional.ofNullable(tripRepository.findByBeginStopAndEndStopAndTrain(sf, sj, solarEnergy)).isPresent()) ?
                    tripRepository.save(
                            Trip.builder()
                            .beginStop(sf)
                            .endStop(sj)
                            .agency(tom)
                            .fare(30)
                            .train(solarEnergy)
                            .journeyTime(30)
                            .build()
                    )
                    : tripRepository.findByBeginStopAndEndStopAndTrain(sf, sj, solarEnergy);
        };
    }
}
