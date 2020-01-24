//import com.fasterxml.jackson.dataformat.yaml._

package matj1.`new`

import java.io.{File, FileInputStream}

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import matj1.`new`.filetree.{DirectoryNode, FileNode, FileTree}

import scala.collection.mutable.ListBuffer

class TemplateReader(templateDir: File) {
	private val StructureFileNameRegex = "structure\\.ya?ml"
	val structureYamlFile: File = try {
		getStructureYamlFile(templateDir).head
	} catch {
		case e: NoSuchElementException => throw new IllegalArgumentException("No structure yaml file found in the directory.", e)
	}
	val yamlParser = new ObjectMapper(new YAMLFactory())

	def parseStructure(): FileTree = {
		val jsonTree = yamlParser.readTree(new FileInputStream(getStructureYamlFile(templateDir).get))

		constructFileTree(jsonTree, this.templateDir.getName)
	}

	/**
	 * Returns a File with the information about the structure of the to-be-created files.
	 * Returns the smallest of the files whose name matches StructureFileNameRegex
	 * or null if there isn't any.
	 *
	 * @param templateDir directory of the template
	 * @return File with the information about the file structure
	 */
	private def getStructureYamlFile(templateDir: File): Option[File] = {
		val potentialStructureFiles =
			this.templateDir.listFiles
			.filter(_.getName.matches(this.StructureFileNameRegex))

		if (potentialStructureFiles.isEmpty) {
			None
		} else {
			Some(potentialStructureFiles.min)
		}
	}

	private def constructFileTree(root: JsonNode, rootName: String): FileTree = {
		if (rootName.endsWith("/")) { // / at the end of name denotes directory
			val contentBuffer = ListBuffer[FileTree]()
			while (root.fields.hasNext) {
				var child = root.fields.next
				contentBuffer.addOne(constructFileTree(child.getValue, child.getKey))
			}
			DirectoryNode(rootName, contentBuffer.toList)
		} else {
			if (!root.isValueNode) {
				System.err.println(s"Supposed content of file ${rootName} is not a value.")
			}
			FileNode(rootName, root.asText)
		}
	}
}
