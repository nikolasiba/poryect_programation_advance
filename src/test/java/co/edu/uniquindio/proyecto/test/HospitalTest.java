package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.Model.Enum.BloodType;
import co.edu.uniquindio.proyecto.Model.Enum.City;
import co.edu.uniquindio.proyecto.Model.Enum.Eps;
import co.edu.uniquindio.proyecto.Model.Enum.Specialization;
import co.edu.uniquindio.proyecto.Services.Interfaces.HospitalServices;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Transactional
@SpringBootTest
public class HospitalTest {

    @Autowired
    private HospitalServices hospitalServices;

    @Test
    public void listBLodType(){
        List<BloodType> answer = hospitalServices.listBLoodType();
        System.out.println(answer);
    }

    @Test
    public void listCity(){
        List<City> answer = hospitalServices.listCity();
        System.out.println(answer);
    }
    @Test
    public void listEps(){
        List<Eps> answer = hospitalServices.listEps();
        System.out.println(answer);
    }
    @Test
    public void listSpecialization(){
        List<Specialization> answer = hospitalServices.listSpecialization();
        System.out.println(answer);
    }

}
