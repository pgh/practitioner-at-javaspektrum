package de.ghadir.practitioner.js_2011_05.model.customer;

/**
 * @author ghadir
 * @since 7/20/11 9:58 PM
 */
public enum CustomerAttributes {

    id( "ID", false ),
    firstName( "First Name" ),
    lastName( "Last Name" ),
    number( "Customer No", Integer.class );

    private String label;
    private boolean isEditable;
    private Class type;

    CustomerAttributes(String label) {
        this( label, true );
    }

    CustomerAttributes(String label, boolean editable) {
        this( label, editable, String.class );
    }

    CustomerAttributes(String label, Class type) {
        this( label, true, type );
    }

    CustomerAttributes(String label, boolean editable, Class type) {
        isEditable = editable;
        this.label = label;
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public Class getType() {
        return type;
    }
}
