package com.crosshairengine.firstgame.engine.Commands;


import java.util.LinkedList;
import java.util.List;

/**
 * Created by NikolaRancic on 5/21/2017.
 */

public class UICommandsListener {
    private List<Command> m_lCommands;

    public UICommandsListener()
    {
        m_lCommands = new LinkedList<Command>();
    }

    public synchronized void addCommand(Command command)
    {
        m_lCommands.add(command);
    }

    public synchronized List<Command> getAllCommands()
    {
        return m_lCommands;
    }

    public synchronized void clearCommandsUI()
    {
        m_lCommands = null;
    }
}
