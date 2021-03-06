package org.tc.osgi.bundle.utils.pattern.command;

import org.junit.jupiter.api.Test;
import org.tc.osgi.bundle.utils.interf.pattern.command.ICommand;
import org.tc.osgi.bundle.utils.interf.pattern.command.exception.CommandExecutionException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * ObserverSubjectTest.java.
 *
 * @author thomas collonvillé
 * @version 0.0.5
 * @req STD_BUNDLE_UTILS_070
 * @track SRS_BUNDLE_UTILS_060
 * @track SDD_BUNDLE_UTILS_080
 */
public class ICommandTest {

    /**
     * test.
     */
    @Test
    public void test() {
        final StringBuffer tmp = new StringBuffer();
        final List<ICommand> cmds = new ArrayList<ICommand>();
        cmds.add(new ICommand() {

            @Override
            public void exec() throws CommandExecutionException {
                tmp.append("cmd1");

            }
        });

        cmds.add(new ICommand() {

            @Override
            public void exec() throws CommandExecutionException {
                tmp.append("cmd2");

            }
        });

        final DefaultRepository rep = DefaultRepository.getInstance();
        rep.setInstructions(cmds);

        try {
            new DefaultRunner().exec();
        } catch (final CommandExecutionException e) {
            fail();
        }

        assertEquals("cmd1cmd2", tmp.toString());
    }

}
