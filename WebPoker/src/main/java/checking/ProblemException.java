/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checking;

/**
 * Class of throwing exceptions
 *
 * @version 3.0
 * @author Jeremiasz Radłowski
 */
public class ProblemException extends Exception {

    /**
     * constructor of ProblemExcetion
     */
    public ProblemException() {
    }

    /**
     * constructor of ProblemExcetion
     * 
     * @param message is a message to return
     */
    public ProblemException(String message) {
        super(message);
    }

}
