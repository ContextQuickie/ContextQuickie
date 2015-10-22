package contextquickie.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ContextQuickie
 *
 *         Wrapper class for starting processes.
 */
public class ProcessWrapper {
	/**
	 * Executes a command with the passed arguments.
	 * @param command The command to execute.
	 * @param arguments The arguments of the command.
	 */
	public static void executeCommand(String command, String... arguments) {
		executeCommand(command, Arrays.asList(arguments));
	}

	/**
	 * Executes a command with the passed arguments.
	 * @param command The command to execute.
	 * @param arguments The arguments of the command.
	 */
	public static void executeCommand(String command, List<String> arguments) {
		List<String> commandAndArguments = new ArrayList<String>();
		commandAndArguments.add(command);
		for (String parameter : arguments) {
			if (parameter.contains(" ")) {
				commandAndArguments.add(StringUtil.QuoteString(parameter));
			} else {
				commandAndArguments.add(parameter);
			}
		}
		ProcessBuilder processBuilder = new ProcessBuilder(commandAndArguments);
		try {
			processBuilder.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
