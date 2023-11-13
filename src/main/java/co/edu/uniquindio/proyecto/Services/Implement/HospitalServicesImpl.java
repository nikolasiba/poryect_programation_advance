package co.edu.uniquindio.proyecto.Services.Implement;

import co.edu.uniquindio.proyecto.Model.Enum.BloodType;
import co.edu.uniquindio.proyecto.Model.Enum.City;
import co.edu.uniquindio.proyecto.Model.Enum.Eps;
import co.edu.uniquindio.proyecto.Model.Enum.Specialization;
import co.edu.uniquindio.proyecto.Services.Interfaces.HospitalServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class HospitalServicesImpl implements HospitalServices {

    @Override
    public List<BloodType> listBLoodType() {
        BloodType[] bloodTypes = BloodType.values();
        List<BloodType> answer = new ArrayList<>();

        for ( BloodType bloodType:bloodTypes ) {
            answer.add(bloodType);
        }

        return answer;

    }

    @Override
    public List<City> listCity() {
        City[] cities = City.values();
        List<City> answer = new ArrayList<>();

        for ( City city:cities ) {
            answer.add(city);
        }

        return answer;
    }

    @Override
    public List<Eps> listEps() {
        Eps[] listEps = Eps.values();
        List<Eps> answer = new ArrayList<>();

        for ( Eps eps:listEps ) {
            answer.add(eps);
        }

        return answer;
    }

    @Override
    public List<Specialization> listSpecialization() {
        Specialization[] specializations = Specialization.values();
        List<Specialization> answer = new ArrayList<>();

        for ( Specialization specialization:specializations ) {
            answer.add(specialization);
        }

        return answer;
    }

}
