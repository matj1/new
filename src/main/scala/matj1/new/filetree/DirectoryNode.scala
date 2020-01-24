package matj1.`new`.filetree

import scala.util.Properties

/**
 * Represents a directory in a file structure.
 *
 * @author matj1
 * @param name name of the directory
 * @param content content of the directory
 */
class DirectoryNode(name: String, var content: Iterable[FileTree] = Iterable()) extends FileTree {
	override def toString: String = {
		val builder = new StringBuilder

		builder.append(s"— ${this.name}:").append(Properties.lineSeparator)
		for (file <- content) {
			for (line <- file.toString.lines.toArray) {
				builder.append(s" |${line}").append(Properties.lineSeparator)
			}
		}

		builder.toString
	}
}

/**
 * Object accompanying DirectoryNode class and implementing apply.
 */
object DirectoryNode {
	/**
	 * Constructs a new DirectoryNode with given parameters.
	 *
	 * @param name name of the directory
	 * @param content content of the directory
	 * @return constructed FileNode with given parameters
	 */
	def apply(name: String, content: Iterable[FileTree] = Iterable()): DirectoryNode = {
        new DirectoryNode(name, content)
    }
}