package com.pl.premier_zone.player;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PlayerService {
    private  PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayers(){
        return playerRepository.findAll();
    }

    public List<Player> getPlayersfromTeam(String teamName){
        return playerRepository.findAll().stream()
                .filter(player->teamName.equals(player.getTeam()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByName(String searchText) {
        return playerRepository.findAll().stream()
                .filter(player -> player.getName().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }


    //to get the player by just position
    public List<Player> getPlayersByPos(String searchText)
    {
        return playerRepository.findAll().stream()
                .filter(player->
                        player.getPos().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }


    //to get the player by nation
    public List<Player> getPlayersByNation(String searchText)
    {
        return playerRepository.findAll().stream()
                .filter(player->
                        player.getNation().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }


    //to get the player by team and position
    public List<Player> getPlayersByTeamAndPosition(String team, String position) {
        return playerRepository.findAll().stream()
                .filter(player -> team.equals(player.getTeam()) && position.equals(player.getPos()))
                .collect(Collectors.toList());
    }

    public Player addPlayer(Player player){
         playerRepository.save(player);
        return player;
    }

    public Player updatePlayer(Player updatedPlayer){
        Optional<Player> existingPlayer=playerRepository.findByname(updatedPlayer.getName());

        if(existingPlayer.isPresent()){
            Player playertoUpdate = existingPlayer.get();
            playertoUpdate.setName(updatedPlayer.getName());
            playertoUpdate.setTeam(updatedPlayer.getTeam());
            playertoUpdate.setPos(updatedPlayer.getPos());
            playertoUpdate.setNation(updatedPlayer.getNation());
            playerRepository.save(playertoUpdate);
            return playertoUpdate;
        }
        return null;
    }
    @Transactional
    public void deletePlayer(String playerName){
        playerRepository.deleteByname(playerName);
    }

}
