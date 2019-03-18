package org.tc.osgi.bundle.utils.pattern.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.tc.osgi.bundle.utils.interf.pattern.command.ICommand;
import org.tc.osgi.bundle.utils.interf.pattern.command.ICommandRepository;

/**
 * Repository.java.
 *
 * @author thomas collonvillé
 * @version 0.0.1
 * @track SDD_BUNDLE_UTILS_080
 */
public class DefaultRepository implements ICommandRepository {

    /**
     * Repository repository.
     */
    private static DefaultRepository repository = null;

    /**
     * getInstance.
     *
     * @return Repository
     */
    public static DefaultRepository getInstance() {
        if (DefaultRepository.repository == null) {
            DefaultRepository.repository = new DefaultRepository();
        }
        return DefaultRepository.repository;
    }

    /**
     * List<Command> commands.
     */
    private List<ICommand> commands = null;

    /**
     * Repository constructor.
     */
    private DefaultRepository() {
        super();
    }

    @Override
    public void addInstruction(final ICommand cmd) {
        getInstructions().add(cmd);

    }

    /**
     * getCommandsIterator.
     *
     * @return CommandsIterator
     */
    @Override
    public Iterator<ICommand> getCommandsIterator() {
        return getInstructions().iterator();
    }

    /**
     * getInstructions.
     *
     * @return List<Command>
     */
    public List<ICommand> getInstructions() {
        if (commands == null) {
            commands = new ArrayList<ICommand>();
        }
        return commands;
    }

    /**
     * setInstructions.
     *
     * @param commands
     *            List<Command>
     */
    public void setInstructions(final List<ICommand> commands) {
        this.commands = commands;
    }

}
