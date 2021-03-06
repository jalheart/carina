/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.models;

import carina.memory.BasicMemoryUnity;
import carina.memory.WorkingMemory;
import carina.metacore.Goal;
import carina.objectlevel.ModelOfTheWorld;

/**
 *
 * @author jalheart
 */
public class TriquiModelOfTheWorld extends ModelOfTheWorld{
    private Board  board;
    
    private String  machine_token   = "O";
    private String  player_token    = "X";
    private Boolean is_me_turn      = false;

    public TriquiModelOfTheWorld() {
        this.board  =new Board();
        this.board.create();
        this.setMission(new Goal());
        if(!board.equals(getStateIs_created())){
            this.setStateIs_created(true);
            this.updateModelOfTheWorld();
        }else{
            this.getBoard().setCells((String[][])WorkingMemory.getInstance().retrieveInformation("cells").information);
        }
    }
    public void changeTurn(){
        this.setStateIs_me_turn(!this.getStateIs_me_turn());
    }
    public String currentToken(){
        return this.getStateIs_me_turn()?this.getMachine_token():this.getPlayer_token();
    }
    /**
     * Agrega los TOKENs usados para cada jugador
     * <p>
     * @param machine_token
     * @param player_token
     * </p>
     */
    public void addTokens(String machine_token, String player_token){
        this.setMachine_token(machine_token);
        this.setPlayer_token(player_token);
    }
    public void addMission(String value){
        if(this.getMission().getCurrentState()!=null){
            this.getMission().getCurrentState().setName(value);
            this.getMission().getCurrentState().setValue(false);
            this.getMission().getTargetState().setName(value);
            this.getMission().getTargetState().setValue(false);
        }
    }
// <editor-fold defaultstate="collapsed" desc="GETs y SETs">
    /**
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(Board board) {
        this.board = board;
    }        
    /**
     * @return the is_me_turn
     */
    public Boolean getStateIs_me_turn() {
        return is_me_turn;
    }

    /**
     * @param is_me_turn the is_me_turn to set
     */
    public void setStateIs_me_turn(Boolean is_me_turn) {
        this.is_me_turn = is_me_turn;
    }
    /**
     * @return the machine_token
     */
    public String getMachine_token() {
        return machine_token;
    }

    /**
     * @param machine_token the machine_token to set
     */
    public void setMachine_token(String machine_token) {
        this.machine_token = machine_token;
    }

    /**
     * @return the player_token
     */
    public String getPlayer_token() {
        return player_token;
    }

    /**
     * @param player_token the player_token to set
     */
    public void setPlayer_token(String player_token) {
        this.player_token = player_token;
    }
// </editor-fold>
    @Override
    public void updateModelOfTheWorld(){
        WorkingMemory.getInstance().storeInformation(new BasicMemoryUnity("cells", this.getBoard().getCells()));
        WorkingMemory.getInstance().storeInformation(new BasicMemoryUnity("is_created", this.getStateIs_created()));
    }
}