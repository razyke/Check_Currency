package com.puper.asuper.checkcurrency;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Daniil Smirnov on 27.06.2017.
 * All copy registered MF.
 */
public class StatisticsFragment extends Fragment {
    private RecyclerView GuessRecyclerView;
    private GuessAdapter guessAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statistics_list_fragment,container,false);
        GuessRecyclerView = (RecyclerView) view
                .findViewById(R.id.gues_recycler_view);
        GuessRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    /**
     * Создаём свой Holder для удобного взаимодействия с адаптером.
     */
    private class GuessHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Guess guess;

        private TextView DataTextView;
        private TextView DescriptionTextView;

        public GuessHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            DataTextView = (TextView) itemView.findViewById(R.id.guess_list_item_date_text_view);
            DescriptionTextView = (TextView) itemView.findViewById(R.id.guess_list_item_answer_text_view);

        }

        public void blindGuess(Guess guess){
            this.guess = guess;
            DataTextView.setText(new SimpleDateFormat("dd.MM.yyyy").format(guess.getDate()));
            String answer = guess.getAnswer();

            if (answer.equals("Time"))
                setDTV("Waiting",Color.GRAY);
            else if (answer.equals("Error"))
                setDTV("Network bad",Color.BLACK);
            else if (answer.equals("Right"))
                //В ручную задаём оранжевый цвет.
                setDTV(answer,Color.argb(255,255,165,0));
            else if (answer.equals("Near"))
                setDTV(answer,Color.GREEN);
            else if (answer.equals("Far"))
                setDTV(answer,Color.RED);
        }

        private void setDTV(String message, int color){
            DescriptionTextView.setText(message);
            DescriptionTextView.setTextColor(color);
        }

        @Override
        public void onClick(View v) {
            if (!(DescriptionTextView.getText().toString().equals("Waiting") ||
                    DescriptionTextView.getText().toString().equals("Network bad"))){
                AlertDialog ad = new AlertDialog.Builder(getActivity())
                        .setTitle("Result")
                        .setMessage("Your guess   "+guess.getValue()+"\n"+"\n"
                        +"True answer   "+guess.getDollarFromCentrobank())
                        .setPositiveButton("Ok", null)
                        .create();
                ad.show();
            }
        }
    }

    private class GuessAdapter extends RecyclerView.Adapter<GuessHolder>{
        private List<Guess> Guesses;

        public GuessAdapter(List<Guess> g){
            Guesses = g;
        }

        @Override
        public GuessHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.guess_list_item,parent,false);
            return new GuessHolder(view);
        }

        @Override
        public void onBindViewHolder(GuessHolder holder, int position) {
            Guess guess = Guesses.get(position);
            holder.blindGuess(guess);

        }

        @Override
        public int getItemCount() {
            return Guesses.size();
        }
    }

    /**
     *   Обновляем вид для пользователя.
     */

    private void updateUI(){
        GuessLab guessLab = GuessLab.get(getActivity());
        List<Guess> guesses = guessLab.getGuesses();

        if (guessAdapter ==null){
            guessAdapter = new GuessAdapter(guesses);
            GuessRecyclerView.setAdapter(guessAdapter);
        }else {
            guessAdapter.notifyDataSetChanged();
        }
    }


}
