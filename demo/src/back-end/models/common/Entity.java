package models.common;

public abstract class Entity {
  private static int nextId = 1;
  private int id;

  public Entity() {
      this.id = nextId++;
  }

  public int getId() {
      return id;
  }

  public void setId(int id) {
      this.id = id;
  }

  public abstract void Validate();
}
