package com.example.persontest.web


import com.example.persontest.entity.Person
import com.example.persontest.entity.PersonMapper
import com.example.persontest.entity.PersonWeb
import com.example.persontest.repo.PersonServiceImpl
import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.kaributools.asc
import com.github.mvysny.kaributools.getColumnBy
import com.github.mvysny.kaributools.setPrimary
import com.github.mvysny.kaributools.sort
import com.vaadin.flow.component.Key
import com.vaadin.flow.component.UI
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.dependency.CssImport
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.page.AppShellConfigurator
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.renderer.ComponentRenderer
import com.vaadin.flow.router.Route
import com.vaadin.flow.server.PWA
import org.springframework.beans.factory.annotation.Autowired


import java.util.*
import kotlin.collections.ArrayList


/**
 * The main view contains a button and a click listener.
 */
@Route("")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
class MainView @Autowired constructor(
    val personServiceImpl: PersonServiceImpl
)  : KComposite() {
    private lateinit var nameField: TextField
    private lateinit var filterByNameField : TextField
    private lateinit var locationField: TextField
    private lateinit var  label :  Label
    private lateinit var addButton: Button
    private lateinit var deleteButton: Button
    private lateinit var filterButton: Button
    private lateinit var grid: Grid<PersonWeb>
    private  var personMapper= PersonMapper()
    private  var persons =ArrayList<PersonWeb>()
    // The main view UI definition
    private val root = ui {
        verticalLayout {
            // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
            addClassName("centered-content")

            // Use TextField for standard text input
            nameField = textField("ФИО")
            locationField = textField("Место жительства")
            // Use Button for a clickable button
            addButton = button("ADD") {
                setPrimary(); addClickShortcut(Key.ENTER)
            }
            label =  Label("Delete Selected Row");
            deleteButton = button("Delete") {
                setPrimary(); addClickShortcut(Key.ENTER)
            }
            filterByNameField = textField("поиск по имени")
            filterButton = button("filter") {
                setPrimary(); addClickShortcut(Key.ENTER)
            }
            grid = grid {
                isExpand = true;
                setSizeFull()
                isHeightByRows = true
                persons = personMapper.getPersonWebList(personServiceImpl.findAll())
                setItems(persons)
                columnFor(PersonWeb::id)
                    .setAutoWidth(true)
                    .setHeader("№")
                columnFor(PersonWeb::name)
                    .setAutoWidth(true)
                    .setHeader("ФИО")
                columnFor(PersonWeb::birthDay)
                    .setAutoWidth(true)
                    .setHeader("ДР")
                columnFor(PersonWeb::location)
                    .setAutoWidth(true)
                    .setHeader("Место жительства")

                sort(PersonWeb::name.asc)
            }

        }
    }

    init {
        // attach functionality to the UI components.
        // It's a good practice to keep UI functionality separated from UI definition.

        // Button click listeners can be defined as lambda expressions
        addButton.onLeftClick {
            var person = Person()
            person.name=nameField.value
            person.location=locationField.value
            person.birthDay= Date()
            personServiceImpl.save(person)
            UI.getCurrent().getPage().reload();
        }

        deleteButton.onLeftClick {
            var personser = grid.selectedItems
            personServiceImpl.deleteById(personser.stream().findFirst().get().id!!)
            UI.getCurrent().getPage().reload();
        }

        filterButton.onLeftClick {
            val filterBar = filterByNameField.value
            grid.setItems(personMapper.getPersonWebListSelected(personServiceImpl.findAll(),filterBar ))

        }
    }
}

@PWA(name = "Project Base for Vaadin", shortName = "Project Base")
class AppShell: AppShellConfigurator
