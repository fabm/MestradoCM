package pt.ipg.mcm.errors.client.tests;

import com.google.common.reflect.ClassPath;
import cz.habarta.typescript.generator.Settings;
import cz.habarta.typescript.generator.TypeScriptGenerator;
import org.junit.Test;
import pt.ipg.mcm.calls.client.model.RetornoRest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainTestTypescript {

  @Test
  public void typescriptTest() throws IOException {
    String pac = RetornoRest.class.getPackage().getName();

    List<Class<?>> classes = new ArrayList<>();

    final ClassLoader loader = Thread.currentThread().getContextClassLoader();

    System.out.println(pac);

    for (final ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
      if (info.getName().startsWith(pac)) {
        classes.add(info.load());
      }
    }

    Settings settings = new Settings();
    settings.moduleName = "testes";

    System.out.println(classes.size());

    TypeScriptGenerator.generateTypeScript(classes,settings,new File("teste.d.ts"));
  }

}
