package ie.tudublin;

public abstract class ArtForms
{
    protected UI ui;
    protected float unit;

    public ArtForms(UI ui, float unit)
    {
        this.ui = ui;
        this.unit = unit/64; // 1 size unit
    }

    public abstract void render();
    

    public abstract void update();


    /**
     * @return the ui
     */
    public UI getUi() {
        return ui;
    }

    /**
     * @param ui the ui to set
     */
    public void setUi(UI ui) {
        this.ui = ui;
    }

    /**
     * @return the unit
     */
    public float getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(float unit) {
        this.unit = unit;
    }

    

}