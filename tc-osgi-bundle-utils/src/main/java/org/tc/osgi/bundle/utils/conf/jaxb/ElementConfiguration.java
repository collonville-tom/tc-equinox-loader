//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, v2.2.4-2
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2014.03.26 at 07:50:01 PM CET
//

package org.tc.osgi.bundle.utils.conf.jaxb;

import java.lang.reflect.Field;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for ElementConfiguration complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ElementConfiguration">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="defaultValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="valueType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fieldName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 * ElementConfiguration.java.
 * @author collonville thomas
 * @version 0.2.2
 * @track SDD_BUNDLE_UTILS_025
 * @track SDD_BUNDLE_UTILS_020
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ElementConfiguration", propOrder = { "defaultValue", "valueType", "fieldName", "className" })
public class ElementConfiguration {

    /**
     * String className.
     */
    @XmlElement(required = true)
    protected String className;
    /**
     * String defaultValue.
     */
    @XmlElement(required = true)
    protected String defaultValue;
    /**
     * String fieldName.
     */
    @XmlElement(required = true)
    protected String fieldName;
    /**
     * String valueType.
     */
    @XmlElement(required = true)
    protected String valueType;

    /**
     * checkClass.
     * @param _obj Object
     * @return boolean
     */
    public boolean checkClass(final Object _obj) {
        if (_obj.getClass().getCanonicalName().equals(className)) {
            return true;
        }
        return false;

    }

    /**
     * checkType.
     * @param _obj Object
     * @return boolean
     * @throws NoSuchFieldException
     * @throws SecurityException
     */
    public boolean checkType(final Object _obj) throws NoSuchFieldException, SecurityException {
        final Field field = _obj.getClass().getDeclaredField(fieldName);
        if (field.getType().getCanonicalName().equals(valueType)) {
            return true;
        }
        return false;
    }

    /**
     * Gets the value of the className property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getClassName() {
        return className;
    }

    /**
     * Gets the value of the defaultValue property.
     *
     * @return possible object is {@link String }
     *
     */
    // TODO faire un retour coherent avec le type definie
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Gets the value of the fieldName property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Gets the value of the valueType property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getValueType() {
        return valueType;
    }

    /**
     * Sets the value of the className property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setClassName(final String value) {
        className = value;
    }

    /**
     * Sets the value of the defaultValue property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setDefaultValue(final String value) {
        defaultValue = value;
    }

    /**
     * Sets the value of the fieldName property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setFieldName(final String value) {
        fieldName = value;
    }

    /**
     * Sets the value of the valueType property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setValueType(final String value) {
        valueType = value;
    }

    /**
     * @return String
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder buff = new StringBuilder("ElementConfiguration[");
        buff.append("defaultValue:").append(defaultValue).append(",");
        buff.append("valueType:").append(valueType).append(",");
        buff.append("fieldName:").append(fieldName).append(",");
        buff.append("className:").append(className).append(",");
        buff.append("]");
        return buff.toString();
    }

}
