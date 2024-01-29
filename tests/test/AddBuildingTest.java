package test;

import Buildings.Building;
import Buildings.Farm;
import Buildings.House;
import board.Board;
import board.City;
import board.Village;
import org.junit.Before;
import org.junit.Test;
import player.Player;

import java.awt.*;

public class AddBuildingTest {
    private City city;
    private Village village;
    private Player player;
    private House house;
    private Farm farm;
    @Before
    public void setUp(){
        player = new Player("test", new Color(0,0,0));
        house = new House();
        farm = new Farm();
        city = new City("test", 0, 0, player, 0, 0);
        village = new Village("test", 0, 0, player, 0);
    }
    @Test
    public void addBuildingToCity() {
        city.addBuilding();
        assert (city.getBuildings().size() == 1);
        assert (city.getBuildings().get(0).getClass() == house.getClass());
    }
    @Test
    public void addBuildingToVillage() {
        village.addBuilding();
        assert (village.getBuildings().size() == 1);
        assert (village.getBuildings().get(0).getClass() == farm.getClass());
    }
    @Test
    public void upgradeBuildings() {
        house.upgrade();
        assert (house.getLevel() == 2);
        farm.upgrade();
        assert (farm.getLevel() == 2);
    }
}
