package matj1.`new`

import java.io.File

import scala.collection.mutable

/**
 * Main class for this program.
 *
 * @author matj1
 */
object New {
	// constants
	private val ExitSuccess = 0
	private val ExitNotFound = 1
	private val ExitOther = 127

	/**
	 * Main method putting all together.
	 *
	 * @param args command line arguments: switches or positional arguments
	 */
	def main(args: Array[String]): Unit = {
		// parse args
		val (parsedArgs, options) = parseArgs(args)

		if (parsedArgs.isEmpty) {
			printUsageAndExit()
		}

		val template = parsedArgs(1)
		val tempdir = getTempDir(template)
		if (tempdir == null) {
			sys.error(s"There is no such template $template.")
			sys.exit(ExitNotFound)
		}

		// read template

		// create files

		// insert files into filesystem

		// exit
		sys.exit(0)
	}

	/**
	 * Prints program usage and exits with success code.
	 */
	private def printUsageAndExit(): Unit = {
		println() // TODO
		sys.exit(ExitSuccess)
	}

	/**
	 * Returns the directory of template with given name.
	 * Searches $HOME/.config/new and /etc/new and looks for directory of the name given by paramtemplate.
	 * Returns null if it doesn't find such directory.
	 *
	 * @param template template name
	 * @return directory of the template
	 */
	private def getTempDir(template: String): File = {
		val dir = new File(sys.env("HOME") + ".config/new/" + template)
		if (dir.exists()) {
			dir
		} else {
			val dirFallback = new File("/etc/new/" + template)
			if (dirFallback.exists()) {
				dirFallback
			} else {
				null
			}
		}
	}

	/**
	 * Returns command-line arguments parsed into a tuple of a list of positional arguments
	 * and a map of options and their values. This function discards args(0), the name of the program.
	 *
	 * @param args an array of command-line arguments
	 * @return arguments divided to positional arguments and switches
	 */
	private def parseArgs(args: Array[String]): (List[String], Map[String, String]) = {
		val options = mutable.Map[String, String]()
		val positionals = mutable.ArrayBuffer[String]()
		val iterator = args.iterator
		try {
			iterator.next()
		} catch {
			case e: NoSuchElementException =>
				throw new IllegalArgumentException("The arg array is empty, there should be a program name.", e)
		}

		while (iterator.hasNext) {
			var arg = iterator.next()

			if (arg.startsWith("-")) {
				try {
					options(arg) = iterator.next()
				} catch {
					case e: NoSuchElementException =>
						throw new IllegalArgumentException("The last option has no value.", e)
				}
			} else {
				positionals += arg
			}
		}

		(positionals.toList, options.toMap)
	}
}
