package co.edu.uniquindio.proyecto.Controllers;

import co.edu.uniquindio.proyecto.Dto.Admin.DoctorDetailDTO;
import co.edu.uniquindio.proyecto.Dto.Admin.DoctorRecordDTO;
import co.edu.uniquindio.proyecto.Dto.Admin.ItemAppointmentAdminDTO;
import co.edu.uniquindio.proyecto.Dto.Admin.ItemDoctorDTO;
import co.edu.uniquindio.proyecto.Dto.Petition.ItemPetitionDTO;
import co.edu.uniquindio.proyecto.Dto.Petition.PetitionDetailDTO;
import co.edu.uniquindio.proyecto.Dto.Petition.RespLogDTO;
import co.edu.uniquindio.proyecto.Exception.AppointmentException.AccountNotFoundException;
import co.edu.uniquindio.proyecto.Exception.AppointmentException.AppointmentsNotFoundException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.DoctorNotFoundException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.DoctorWithEmailRepeatedException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.DoctorWithIdRepeatedException;
import co.edu.uniquindio.proyecto.Exception.DoctorExceptions.DoctorsNotFoundException;
import co.edu.uniquindio.proyecto.Exception.PetitionExceptions.PetitionNotFoundException;
import co.edu.uniquindio.proyecto.Exception.PetitionExceptions.PetitionsNotFoundException;
import co.edu.uniquindio.proyecto.Model.Enum.PetitionState;
import co.edu.uniquindio.proyecto.Services.Interfaces.AdminServices;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@SecurityRequirement(name = "bearerAuth")

public class AdminController {

    private final AdminServices adminServices;

    @PostMapping("/create-doctor")
    public int createDoctor(@Valid @RequestBody DoctorRecordDTO doctorRecordDTO) throws DoctorWithIdRepeatedException, DoctorWithEmailRepeatedException{
        return adminServices.createDoctor(doctorRecordDTO);
    }

    @PutMapping("/update-doctor")
    public int updateDoctor(@Valid @RequestBody DoctorDetailDTO doctorDTO) throws DoctorNotFoundException{
        return adminServices.updateDoctor(doctorDTO);
    }

    @PutMapping("/delete-doctor/{code}")
    public void deleteDoctor(@PathVariable int code) throws DoctorNotFoundException{
        adminServices.deleteDoctor(code);
    }

    @GetMapping("/get-doctors")
    public List<ItemDoctorDTO> ListDoctors() throws DoctorsNotFoundException{
        return adminServices.ListDoctors();
    }

    @GetMapping("/get-doctor/{code}")
    public DoctorDetailDTO getDoctor(@PathVariable int code) throws DoctorNotFoundException{
        return adminServices.getDoctor(code);
    }

    @GetMapping("/get-petitions")
    public List<ItemPetitionDTO> listPetition() throws PetitionsNotFoundException{
        return adminServices.listPetition();
    }

    @GetMapping("/choosed-petition/{code}")
    public PetitionDetailDTO showDetailPetition(@PathVariable int code) throws PetitionNotFoundException{
        return adminServices.showDetailPetition(code);
    }

    @PostMapping("/respond-petition")
    public int respondPetition(@Valid @RequestBody RespLogDTO respLogDTO) throws PetitionNotFoundException,
            AccountNotFoundException{
        return adminServices.respondPetition(respLogDTO);
    }

    @PutMapping("/change-state-petition/{petitionCode}")
    public void changePetitionState(@PathVariable int petitionCode, @Valid @RequestBody PetitionState petitionState)
            throws PetitionNotFoundException{
        adminServices.changePetitionState(petitionCode, petitionState);
    }

    @GetMapping("/get-appointment")
    public List<ItemAppointmentAdminDTO> listAppointment() throws AppointmentsNotFoundException{
        return adminServices.listAppointment();
    }


}
