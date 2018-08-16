/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import model.University;
import java.util.ArrayList;

/**
 *
 * @author Noid
 */
public interface HighlightDAO {
    public int assignHighlights( ArrayList<String> names );
    
    public University getHighlight(int select);
    
    
}
