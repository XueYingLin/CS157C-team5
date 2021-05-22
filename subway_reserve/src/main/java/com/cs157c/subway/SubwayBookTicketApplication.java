package com.cs157c.subway;

import com.cs157c.subway.model.train.Agency;
import com.cs157c.subway.model.train.Station;
import com.cs157c.subway.model.train.Train;
import com.cs157c.subway.model.train.Trip;

import com.cs157c.subway.repository.train.AgencyRepository;
import com.cs157c.subway.repository.train.TrainRepository;
import com.cs157c.subway.repository.train.StationRepository;
import com.cs157c.subway.repository.train.TripRepository;

import com.cs157c.subway.model.user.Role;
import com.cs157c.subway.model.user.User;

import com.cs157c.subway.repository.user.RoleRepository;
import com.cs157c.subway.repository.user.UserRepository;

import java.util.Optional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;






@SpringBootApplication
public class SubwayBookTicketApplication {

    public static void main(String[] args) {
        System.out.println("When you run this project, it will auto init some data insert into my mongodb DB. Only Once!");
        SpringApplication.run(SubwayBookTicketApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StationRepository stationRepository, RoleRepository roleRepository, UserRepository userRepository,
                           AgencyRepository agencyRepository, TrainRepository trainRepository, TripRepository tripRepository
    ) {

        return args -> {

            /* init our roles */

            Role adminRole = new Role();
            adminRole.setRole("ADMIN");


            adminRole = (!Optional.ofNullable(roleRepository.findByRole("ADMIN")).isPresent()) ? roleRepository.save(adminRole) :  roleRepository.findByRole("ADMIN");



            Role traveller = new Role();
            traveller.setRole("PASSENGER");


            traveller = (! Optional.ofNullable(roleRepository.findByRole("TRAVELLER")).isPresent()) ? roleRepository.save(traveller) : roleRepository.findByRole("ADMIN");





            /* Create my five railway stations */

            Station sf = new Station();
            sf.setName("San Francisco");
            sf.setDetail("near SF airport");
            sf.setCode("SF");

            sf = (!Optional.ofNullable(stationRepository.findByCode("SF")).isPresent()) ? stationRepository.save(sf) : stationRepository.findByCode("SF");



            Station sm = new Station();
            sm.setName("San Mateo");
            sm.setDetail("near San Mateo");
            sm.setCode("SM");

            sm = (!Optional.ofNullable(stationRepository.findByCode("SM")).isPresent()) ? stationRepository.save(sm) : stationRepository.findByCode("SM");



            Station rw = new Station();
            rw.setName("Red Wood");
            rw.setDetail("near Facebook");
            rw.setCode("RW");

            rw = (!Optional.ofNullable(stationRepository.findByCode("RW")).isPresent()) ? stationRepository.save(rw) : stationRepository.findByCode("RW");


            Station pa = new Station();
            pa.setName("Palo Alto");
            pa.setDetail("near iKEA");
            pa.setCode("PA");

            pa = (!Optional.ofNullable(stationRepository.findByCode("PA")).isPresent()) ? stationRepository.save(pa) : stationRepository.findByCode("PA");



            Station sj = new Station();
            sj.setName("San Jose");
            sj.setDetail("near San Mateo");
            sj.setCode("SJ");

            sj = (!Optional.ofNullable(stationRepository.findByCode("SJ")).isPresent()) ? stationRepository.save(sj) : stationRepository.findByCode("SJ");








            /* create admin user */

            User admin = new User();
            admin.setEmail("admin.user@gmail.com");
            admin.setFirstName("Tim");
            admin.setLastName("cook");
            admin.setPassword("$2a$10$7PtcjEnWb/ZkgyXyxY1/Iei2dGgGQUbqIIll/dt.qJ8l8nQBWMbYO"); //123456
            admin.setPhoneNumber("4082222222");
            admin.setRoles(new HashSet<>(Arrays.asList(adminRole)));

            admin = (!Optional.ofNullable(userRepository.findByEmail("admin.user@gmail.com")).isPresent()) ? userRepository.save(admin) : userRepository.findByEmail("admin.user@gmail.com");






            /* init a agency */
            Agency tom = new Agency();
            tom.setCode("AGENCY-TOM");
            tom.setName("Tom Agency");
            tom.setDetails("near the moon");
            tom.setOwner(admin);

            tom = (!Optional.ofNullable(agencyRepository.findByCode("AGENCY-TOM")).isPresent()) ? agencyRepository.save(tom) : agencyRepository.findByCode("AGENCY-TOM");






            /* init a train */
            Train solarEnergy = new Train();
            solarEnergy.setCode("AGENCY-TIM");
            solarEnergy.setAgency(tom);
            solarEnergy.setCapacity(100);

            solarEnergy = (!Optional.ofNullable(trainRepository.findByCode("AGENCY-TIM")).isPresent()) ? trainRepository.findByCode("AGENCY-TIM") : trainRepository.findByCode("AGENCY-TIM");






            /* Adding my train into an agency */
            if (tom.getTrains() == null) {
                Set<Train> trains = new HashSet<>();
                tom.setTrains(new HashSet<>());
                tom.getTrains().add(solarEnergy);
                agencyRepository.save(tom);
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





