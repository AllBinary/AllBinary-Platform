/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.allbinary.input.automation.actions.script;

/**
 *
 * @author user
 */
public class JTreeInterfaceFactory {
    
    private static final JTreeInterfaceFactory instance = new JTreeInterfaceFactory();

    /**
     * @return the instance
     */
    public static JTreeInterfaceFactory getInstance() {
        return instance;
    }
    
    private JTreeInterface jTreeInterface;
            
    public void set(JTreeInterface jTreeInterface)
    {
        this.jTreeInterface = jTreeInterface;
    }
    
    public JTreeInterface getJTreeInterface()
    {
        return this.jTreeInterface;
    }
}
