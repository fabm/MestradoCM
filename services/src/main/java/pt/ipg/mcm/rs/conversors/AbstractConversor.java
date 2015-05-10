package pt.ipg.mcm.rs.conversors;

/**
 *
 * @param <S> Source type
 * @param <D> Destiny type
 */
public abstract class AbstractConversor<S, D> {

  protected S source;

  public AbstractConversor(S source) {
    this.source = source;
  }

  public abstract D converted();

}
