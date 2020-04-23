package es.upo.tfg.manuelgandul.appkarate.service.empleado.impl;

import es.upo.tfg.manuelgandul.appkarate.converter.empleado.EmpleadoConverter;
import es.upo.tfg.manuelgandul.appkarate.entity.empleado.Empleado;
import es.upo.tfg.manuelgandul.appkarate.entity.empleado.TipoUsuario;
import es.upo.tfg.manuelgandul.appkarate.model.empleado.EmpleadoDto;
import es.upo.tfg.manuelgandul.appkarate.repository.empleado.EmpleadoJpaRepository;
import es.upo.tfg.manuelgandul.appkarate.service.empleado.EmpleadoService;
import es.upo.tfg.manuelgandul.appkarate.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service("empleadoService")
public class EmpleadoServiceImpl implements EmpleadoService, UserDetailsService {

    @Autowired
    @Qualifier("empleadoJpaRepository")
    private EmpleadoJpaRepository empleadoJpaRepository;

    @Autowired
    @Qualifier("empleadoConverter")
    private EmpleadoConverter empleadoConverter;

    @Override
    public List<EmpleadoDto> listEmpleados() {
        List<EmpleadoDto> empleadoDtoList = new ArrayList<>();

        empleadoJpaRepository.findAll().stream().forEach((empleado) -> {
            if(empleado.isProfesor() == true){
                empleadoDtoList.add(empleadoConverter.entity2model(empleado));
            }
        });

        return empleadoDtoList;
    }

    @Override
    public List<EmpleadoDto> listProfesores() {
        List<EmpleadoDto> empleadoDtoList = new ArrayList<>();

        empleadoJpaRepository.findAll().stream().forEach((empleado) -> {
            if(empleado.isProfesor() == false) {
                empleadoDtoList.add(empleadoConverter.entity2model(empleado));
            }
        });

        return empleadoDtoList;
    }

    @Override
    public EmpleadoDto addEmpleado(EmpleadoDto empleadoDto) {
        Empleado empleado = empleadoConverter.model2entity(empleadoDto);

        empleadoDto = empleadoConverter.entity2model(empleadoJpaRepository.save(empleado));

        return empleadoDto;
    }

    @Override
    public void removeEmpleado(EmpleadoDto empleadoDto) {
        empleadoJpaRepository.delete(empleadoConverter.model2entity(empleadoDto));
    }

    @Override
    public EmpleadoDto updateEmpleado(EmpleadoDto empleadoDto) {
        empleadoDto = empleadoConverter.entity2model(empleadoJpaRepository.save(empleadoConverter.model2entity(empleadoDto)));

        return empleadoDto;
    }

    @Override
    public EmpleadoDto getEmpleadoById(int id) {
        return empleadoConverter.entity2model(empleadoJpaRepository.findById(id));
    }

    @Override
    public EmpleadoDto getEmpleadoByDni(String dni) {
        return empleadoConverter.entity2model(empleadoJpaRepository.findByDni(dni));
    }

    @Override
    public EmpleadoDto getUserAuthenticated() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return getEmpleadoByDni(user.getUsername());
    }

    @Override
    public EmpleadoDto loginRest(String user, String password) {
        EmpleadoDto empleadoDto = null;
        Empleado empleado = empleadoJpaRepository.findByDni(user);

        if(Utility.matchPassword(password, empleado.getContrasenya())){
            empleadoDto = empleadoConverter.entity2model(empleado);
        }

        return empleadoDto;
    }

    @Override
    public boolean isLogged(String user, String token) {
        return null != empleadoJpaRepository.findByDniAndToken(user, token);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Empleado empleado =empleadoJpaRepository.findByDni(s);
        List<GrantedAuthority> authorities = buildAutorities(empleado.getTipo_usuario());

        return buildUser(empleado, authorities);
    }

    private User buildUser(Empleado empleado, List<GrantedAuthority> authorities){
        return new User(empleado.getDni(), empleado.getContrasenya(), empleado.isActivo(), true,
                true,true, authorities);
    }

    private List<GrantedAuthority> buildAutorities(Set<TipoUsuario> tiposUsuario){
        Set<GrantedAuthority> auths = new HashSet<>();

        for(TipoUsuario tipoUsuario : tiposUsuario){
            auths.add(new SimpleGrantedAuthority(tipoUsuario.getTipo()));
        }

        return new ArrayList<>(auths);
    }


}
