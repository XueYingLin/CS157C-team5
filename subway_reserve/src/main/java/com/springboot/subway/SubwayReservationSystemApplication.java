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

            role adminRole = new role();
            adminRole.setRole("ADMIN");


            adminRole = (!Optional.ofNullable(roleRepository.findByRole("ADMIN")).isPresent()) ? roleRepository.save(adminRole) :  roleRepository.findByRole("ADMIN");



            role traveller = new role();
            traveller.setRole("TRAVELLER");


            traveller = (! Optional.ofNullable(roleRepository.findByRole("TRAVELLER")).isPresent()) ? roleRepository.save(traveller) : roleRepository.findByRole("ADMIN");





            /* Create my five railway stations */

            station sf = new station();
            sf.setName("San Francisco");
            sf.setDetail("near SF airport");
            sf.setCode("SF");

            sf = (!Optional.ofNullable(stationRepository.findByCode("SF")).isPresent()) ? stationRepository.save(sf) : stationRepository.findByCode("SF");



            station sm = new station();
            sm.setName("San Mateo");
            sm.setDetail("near San Mateo");
            sm.setCode("SM");

            sm = (!Optional.ofNullable(stationRepository.findByCode("SM")).isPresent()) ? stationRepository.save(sm) : stationRepository.findByCode("SM");



            station rw = new station();
            rw.setName("Red Wood");
            rw.setDetail("near Facebook");
            rw.setCode("RW");

            rw = (!Optional.ofNullable(stationRepository.findByCode("RW")).isPresent()) ? stationRepository.save(rw) : stationRepository.findByCode("RW");


            station pa = new station();
            pa.setName("Palo Alto");
            pa.setDetail("near iKEA");
            pa.setCode("PA");

            pa = (!Optional.ofNullable(stationRepository.findByCode("PA")).isPresent()) ? stationRepository.save(pa) : stationRepository.findByCode("PA");



            station sj = new station();
            sj.setName("San Jose");
            sj.setDetail("near San Mateo");
            sj.setCode("SJ");

            sj = (!Optional.ofNullable(stationRepository.findByCode("SJ")).isPresent()) ? stationRepository.save(sj) : stationRepository.findByCode("SJ");






            /* create admin user */

            User admin = new User();
            admin.setEmail("admin.user@gmail.com");
            admin.setFirstName("Tim");
            admin.setLastName("cook");
            admin.setPassword("123456");
            admin.setPhoneNumber("4082222222");
            admin.setRoles(new ArrayList<>(Arrays.asList(adminRole)));

            admin = (!Optional.ofNullable(userRepository.findByEmail("admin.user@gmail.com")).isPresent()) ? userRepository.save(admin) : userRepository.findByEmail("admin.user@gmail.com");




            /* init a agency */
            Agency tom = new Agency();
            tom.setCode("AGENCY-TOM");
            tom.setName("Tom Agency");
            tom.setDetails("near the moon");
            tom.setUser(admin);

            tom = (!Optional.ofNullable(agencyRepository.findByCode("AGENCY-TOM")).isPresent()) ? agencyRepository.save(tom) : agencyRepository.findByCode("AGENCY-TOM");




            /* init a train */
            Train solarEnergy = new Train();
            solarEnergy.setCode("AGENCY-TIM");
            solarEnergy.setAgency(tom);
            solarEnergy.setCapacity(100);

            solarEnergy = (!Optional.ofNullable(trainRepository.findByCode("AGENCY-TIM")).isPresent()) ? trainRepository.findByCode("AGENCY-TIM") : trainRepository.findByCode("AGENCY-TIM");



            /* Adding my train into an agency */

            if (tom.getTrains() == null) {
                tom.setTrains(new ArrayList<>());
                tom.getTrains().add(solarEnergy);
            }




            /* init a trip (for a ticket) */
            Trip trip = new Trip();
            trip.setBeginStation(sf);
            trip.setEndStation(sj);
            trip.setAgency(tom);
            trip.setFare(30);
            trip.setTrain(solarEnergy);
            trip.setJourneyTime(30);

            trip = (!Optional.ofNullable(tripRepository.findByBeginStationAndEndStationAndTrain(sf, sj, solarEnergy)).isPresent()) ?
                    tripRepository.save(trip) : tripRepository.findByBeginStationAndEndStationAndTrain(sf, sj, solarEnergy);

        };
    }


}
