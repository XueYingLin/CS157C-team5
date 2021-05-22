package com.cs157c.subway.service;



import com.cs157c.subway.dto.mapper.TripMapper;
import com.cs157c.subway.dto.model.train.AgencyDto;
import com.cs157c.subway.dto.model.train.TrainDto;
import com.cs157c.subway.dto.model.train.StationDto;
import com.cs157c.subway.dto.model.train.TripDto;
import com.cs157c.subway.dto.model.user.UserDto;
import com.cs157c.subway.model.train.Agency;
import com.cs157c.subway.model.train.Station;
import com.cs157c.subway.model.train.Train;
import com.cs157c.subway.model.train.Trip;
import com.cs157c.subway.repository.train.AgencyRepository;
import com.cs157c.subway.repository.train.TrainRepository;
import com.cs157c.subway.repository.train.StationRepository;
import com.cs157c.subway.repository.train.TripRepository;
import com.cs157c.subway.model.user.User;
import com.cs157c.subway.repository.user.UserRepository;
import com.cs157c.subway.util.RandomStringUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;




@Component
public class SubwayReservationService {
    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private StationRepository stationRepository;


    @Autowired
    private TripRepository tripRepository;



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;






    public Set<StationDto> getAllStations() {
        return stationRepository.findAll()
                .stream()
                .map(station -> modelMapper.map(station, StationDto.class))
                .collect(Collectors.toCollection(TreeSet::new));
    }




    public StationDto getStationByCode(String stationCode) throws Exception {
        Optional<Station> station = Optional.ofNullable(stationRepository.findByCode(stationCode));
        if (station.isPresent()) {
            return modelMapper.map(station.get(), StationDto.class);
        } else {
            throw new Exception("No such station.");
        }
    }





    public AgencyDto getAgency(UserDto userDto) throws Exception {
        User user = getUser(userDto.getEmail());
        if (user != null) {
            Optional<Agency> agency = Optional.ofNullable(agencyRepository.findByOwner(user));
            if (agency.isPresent()) {
                return modelMapper.map(agency.get(), AgencyDto.class);
            } else {
                throw new Exception("The agency is not found!");
            }
        }else {
            throw new Exception("The user is nof found!");
        }
    }







    public AgencyDto addAgency(AgencyDto agencyDto) throws Exception {
        User admin = getUser(agencyDto.getOwner().getEmail());
        if (admin != null) {
            Optional<Agency> agency = Optional.ofNullable(agencyRepository.findByName(agencyDto.getName()));
            if (!agency.isPresent()) {
                Agency agencyModel = new Agency();

                agencyModel.setName(agencyDto.getName());
                agencyModel.setDetails(agencyDto.getDetails());
                agencyModel.setCode(RandomStringUtil.getAlphaNumericString(8, agencyDto.getName()));
                agencyModel.setOwner(admin);
                agencyRepository.save(agencyModel);

                return modelMapper.map(agencyModel, AgencyDto.class);
            } else {
                throw new Exception("The agency is duplicated!");
            }
        } else {
            throw new Exception("The user is not found!");
        }
    }






    @Transactional
    public AgencyDto updateAgency(AgencyDto agencyDto, TrainDto trainDto) throws Exception {
        Agency agency = getAgency(agencyDto.getCode());
        if (agency != null) {
            if (trainDto != null) {
                Optional<Train> bus = Optional.ofNullable(trainRepository.findByCodeAndAgency(trainDto.getCode(), agency));
                if (!bus.isPresent()) {

                    Train trainModel = new Train();
                    trainModel.setAgency(agency);
                    trainModel.setCode(trainDto.getCode());
                    trainModel.setCapacity(trainDto.getCapacity());
                    trainModel.setMake(trainDto.getMake());

                    trainRepository.save(trainModel);
                    if (agency.getTrains() == null) {
                        agency.setTrains(new HashSet<>());
                    }
                    agency.getTrains().add(trainModel);
                    return modelMapper.map(agencyRepository.save(agency), AgencyDto.class);
                } else {
                    throw new Exception("the train is duplicated");
                }
            } else {
                //update agency details case
                agency.setName(agencyDto.getName());
                agency.setDetails(agencyDto.getDetails());
                agency.setContactInfo(agencyDto.getContactInfo());
                agency.setAddress(agencyDto.getAddress());
                agency.setOfficeHour(agencyDto.getOfficeHour());


                return modelMapper.map(agencyRepository.save(agency), AgencyDto.class);
            }
        } else {
            throw new Exception("This agency is not found!");
        }
    }





    public TripDto getTripById(String tripID) throws Exception {
        Optional<Trip> trip = tripRepository.findById(tripID);
        if (trip.isPresent()) {
            return TripMapper.toTripDto(trip.get());
        } else {
            throw new Exception("No such trip!");
        }
    }








    @Transactional
    public List<TripDto> addTrip(TripDto tripDto) {
        String beginStationCode = tripDto.getBeginStationCode();
        String endStationCode = tripDto.getEndStationCode();
        String agencyCode = tripDto.getAgencyCode();
        String trainCode = tripDto.getTrainCode();

        Station beginStation = stationRepository.findByCode(beginStationCode);
        Station endStation = stationRepository.findByCode(endStationCode);
        Agency agency = agencyRepository.findByCode(agencyCode);
        Train train = trainRepository.findByCode(trainCode);

        List<TripDto> trips = new ArrayList<>(2);
        Trip toTrip = new Trip();
        toTrip.setBeginStation(beginStation);
        toTrip.setEndStation(endStation);
        toTrip.setAgency(agency);
        toTrip.setTrain(train);
        toTrip.setJourneyTime(tripDto.getJourneyTime());
        toTrip.setFare(tripDto.getFare());
        trips.add(TripMapper.toTripDto(tripRepository.save(toTrip)));

        return trips;
    }











    public List<TripDto> getAgencyTrips(String agencyCode) throws Exception {
        Agency agency = getAgency(agencyCode);
        if (agency != null) {
            List<Trip> agencyTrips = tripRepository.findByAgency(agency);
            if (!agencyTrips.isEmpty()) {
                return agencyTrips
                        .stream()
                        .map(trip -> TripMapper.toTripDto(trip))
                        .collect(Collectors.toList());
            }
            return Collections.emptyList();
        } else {
            throw new Exception("No such agency!");
        }
    }













    private List<Trip> findTripsBetweenStations(String beginStationCode, String  endStationCode) throws Exception {
        Optional<Station> beginStation = Optional
                .ofNullable(stationRepository.findByCode(beginStationCode));
        if (beginStation.isPresent()) {
            Optional<Station> endStation = Optional
                    .ofNullable(stationRepository.findByCode(endStationCode));
            if (endStation.isPresent()) {
                List<Trip> availableTrips = tripRepository.findAllByBeginStationAndEndStation(beginStation.get(), endStation.get());
                if (!availableTrips.isEmpty()) {
                    return availableTrips;
                }
                return Collections.emptyList();
            } else {
                throw new Exception("This station is not found!");
            }
        } else {
            throw new Exception("station is not found!");
        }
    }





    public List<TripDto> getAvailableTripsBetweenStations(String beginStationCode, String endStationCode) throws Exception {
        List<Trip> availableTrips = findTripsBetweenStations(beginStationCode, endStationCode);
        if (!availableTrips.isEmpty()) {
            return availableTrips
                    .stream()
                    .map(trip -> TripMapper.toTripDto(trip))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }






    private User getUser(String email) {
        return userRepository.findByEmail(email);
    }



    private Station getStation(String stationCode) {
        return stationRepository.findByCode(stationCode);
    }



    private Train getTrain(String trainCode) {
        return trainRepository.findByCode(trainCode);
    }





    private Agency getAgency(String agencyCode) {
        return agencyRepository.findByCode(agencyCode);
    }


}
