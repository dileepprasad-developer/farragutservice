package controllers

import java.io.IOException
import java.lang.reflect.{Type, ParameterizedType}
import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.core.JsonGenerationException
import com.fasterxml.jackson.databind.{DeserializationFeature, JsonMappingException, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.`type`.TypeReference

object JacksonWrapper {
  val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)
  mapper.writerWithDefaultPrettyPrinter();
  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
  mapper.setSerializationInclusion(Include.NON_NULL)

  def serialize(value: Any): String = {
    import java.io.StringWriter
    val writer = new StringWriter()

    try {
      mapper.writeValue(writer, value)
    }
    catch {
      case exc: IOException => {
        throw new FarragutException(exc.getLocalizedMessage , exc.getCause)
      }
      case exc: JsonGenerationException => {
        throw new FarragutException(exc.getLocalizedMessage , exc.getCause)
      }
      case exc: JsonMappingException => {
        throw new FarragutException(exc.getLocalizedMessage , exc.getCause)
      }
    }
    writer.toString
  }
  def deserialize[T: Manifest](value: String): T =

    try {
      mapper.readValue(value, typeReference[T])
    }
    catch {
      case exc: IOException => {
        throw new FarragutException(exc.getLocalizedMessage , exc.getCause)
      }
      case exc: JsonGenerationException => {
        throw new FarragutException(exc.getLocalizedMessage , exc.getCause)
      }
      case exc: JsonMappingException => {
        throw new FarragutException(exc.getLocalizedMessage , exc.getCause)
      }
    }

  private[this] def typeReference[T: Manifest] = new TypeReference[T] {
    override def getType = typeFromManifest(manifest[T])
  }

  private[this] def typeFromManifest(m: Manifest[_]): Type = {
    if (m.typeArguments.isEmpty) {
      m.runtimeClass
    }
    else new ParameterizedType {
      def getRawType = m.runtimeClass

      def getActualTypeArguments = m.typeArguments.map(typeFromManifest).toArray

      def getOwnerType = null
    }
  }
}