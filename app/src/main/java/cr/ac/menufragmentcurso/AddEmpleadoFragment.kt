package cr.ac.menufragmentcurso

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import cr.ac.menufragmentcurso.entity.Empleado
import cr.ac.menufragmentcurso.repository.EmpleadoRepository

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddEmpleadoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddEmpleadoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1 : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_add_empleado, container, false)
        val btnAgregar = view.findViewById<Button>(R.id.agregarButton) as Button

        btnAgregar.setOnClickListener {
            val id1 = view.findViewById<TextView>(R.id.editID).text
            val nombre = view.findViewById<TextView>(R.id.editNombre).text
            val departamento = view.findViewById<TextView>(R.id.editDepartamento).text
            val puesto = view.findViewById<TextView>(R.id.editPuesto).text


            val builder = AlertDialog.Builder(context)
            builder.setMessage("¿Desea Agregar este registro?")
                .setCancelable(false)
                .setPositiveButton("Sí") { dialog, id ->


                    val empleado : Empleado  =  Empleado(null, id1.toString(),nombre.toString(),
                        puesto.toString(),departamento.toString(),"")
                    EmpleadoRepository.instance.save(empleado)
                    val fragment : Fragment = CameraFragment.newInstance("Camera")
                    fragmentManager?.beginTransaction()?.replace(R.id.home_content, fragment)
                        ?.commit()
                }
                .setNegativeButton(
                    "No"
                ) { dialog, id ->
                    // logica del no
                }
            val alert = builder.create()
            alert.show()

        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddEmpleadoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            AddEmpleadoFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}