package matj1.`new`.filetree

import scala.util.Properties

/**
 * The implementation of file node of a FileTree.
 *
 * @author matj1
 * @param name the name of the file
 * @param content content of the file
 */
class FileNode(name: String, var content: String = "") extends FileTree {
	override def toString: String = {
		val builder = new StringBuilder

		builder.append(s"— ${name}:").append(Properties.lineSeparator)
		for (line <- content.lines.toArray) {
			builder.append(line).append(Properties.lineSeparator)
		}

		builder.toString
	}
}

/**
 * Object accompanying FileNode class and implementing apply.
 */
object FileNode {
	/**
	 * Constructs a new FileNode with given parameters.
	 *
	 * @param name name of the file
	 * @param content content of the file
	 * @return constructed FileNode with given parameters
	 */
	def apply(name: String, content: String = ""): FileNode = {
		new FileNode(name, content)
	}
}
