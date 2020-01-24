package matj1.`new`.filetree

/**
 * An enumeration of FileTree node types.
 *
 * @author matj1
 */
object NodeType extends Enumeration {
	type NodeType = Value
	val FileNode, DirectoryNode = Value
}
