package cr.ac.menufragmentcurso.repository

import cr.ac.menufragmentcurso.entity.Empleado
import cr.ac.menufragmentcurso.service.EmpleadoService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EmpleadoRepository {
    val empleadoService : EmpleadoService

    companion object {
            val instance by lazy{
                EmpleadoRepository().apply {   }
            }
    }

    constructor(){

        val retrofit = Retrofit.Builder().baseUrl("https://etiquicia.click/restAPI/api.php/records/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        empleadoService = retrofit.create(EmpleadoService::class.java)

    }

     fun save(empleado: Empleado){
        //empleados.put(empleado.id, empleado)
         empleadoService.add(empleado).execute()

    }
    fun edit(empleado: Empleado){
       // empleados.put(empleado.id, empleado)
        empleado.idEmpleado?.let { empleadoService.update(it, empleado).execute() }
    }
    fun delete(empleado: Empleado){
        //empleados.remove(id)
        empleado.idEmpleado?.let { empleadoService.delete(it).execute() }
    }

    fun datos (): List<Empleado>{
        //return empleados.values.toList()
        return empleadoService.getEmpleado().execute().body()!!.records
    }
}