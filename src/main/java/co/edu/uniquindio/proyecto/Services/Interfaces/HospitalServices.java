package co.edu.uniquindio.proyecto.Services.Interfaces;

import co.edu.uniquindio.proyecto.Model.Enum.BloodType;
import co.edu.uniquindio.proyecto.Model.Enum.City;
import co.edu.uniquindio.proyecto.Model.Enum.Eps;
import co.edu.uniquindio.proyecto.Model.Enum.Specialization;

import java.util.List;

public interface HospitalServices {

    public List<BloodType> listBLoodType();
    public List<City> listCity();
    public List<Eps> listEps();
    public List<Specialization> listSpecialization();


}
