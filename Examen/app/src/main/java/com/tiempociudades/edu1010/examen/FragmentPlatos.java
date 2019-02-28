package com.tiempociudades.edu1010.examen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class FragmentPlatos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    RecyclerView usersRecycler;
    List<Platos> platos = new ArrayList<>();
    MiAdapter miAdapter = new MiAdapter(platos);




    public FragmentPlatos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPlatos.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPlatos newInstance(String param1, String param2) {
        FragmentPlatos fragment = new FragmentPlatos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_fragment_platos, container, false);
        usersRecycler = view.findViewById(R.id.RecyclerView);
        usersRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        usersRecycler.setAdapter(miAdapter);


       return view;

    }
public void añadirLista(List<Platos> platos){
        this.platos=platos;
        miAdapter.notifyDataSetChanged();


}







    //Crear estas dos clases para el recicler, userViewHolder y MiAdapter
    public class UserViewHolder extends RecyclerView.ViewHolder{
        //Declaramos todo lo del xml que se tenga que mostrar y haya que modificar cada vez
        TextView nombrePlato;
        TextView ingredientes;
        TextView precio;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            //aqui buscamos la referencia e el xml
            nombrePlato=itemView.findViewById(R.id.nombre);
            ingredientes=itemView.findViewById(R.id.ingedientes);
            precio=itemView.findViewById(R.id.precio);
        }
    }


    public class MiAdapter extends RecyclerView.Adapter<FragmentPlatos.UserViewHolder>{


        List<Platos> platos = new ArrayList<>();
        public MiAdapter(List<Platos> userslistadapter) {
            this.platos = platos;
        }
        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemview = getLayoutInflater()
                    .inflate(R.layout.lista, viewGroup, false);


            return  new FragmentPlatos.UserViewHolder(itemview);
        }


        @Override
        public void onBindViewHolder(@NonNull FragmentPlatos.UserViewHolder userViewHolder, int i) {
            userViewHolder.nombrePlato.setText(platos.get(i).getNombre());
            userViewHolder.ingredientes.setText(String.valueOf(platos.get(i).getIngredientes()));
            userViewHolder.precio.setText(String.valueOf(platos.get(i).getPrecio()));
        }

        @Override
        public int getItemCount() {

            return platos.size();
        }


    }




}
