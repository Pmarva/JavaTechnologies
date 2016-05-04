package ee.itcollege;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends WebPage {
    private TextField textField;
    private Form form;
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);
        final Model<String> nimetus = new Model<String>("");

        final WebMarkupContainer listContainer = new WebMarkupContainer("listContainer");
        listContainer.setOutputMarkupId(true);


        final List<String> list = new ArrayList<String>();
        list.add("Mine poodi");
        list.add("Mine jooksma");

        final ListView toDoList = new ListView("listitem", list) {

                protected void populateItem(ListItem item) {

                    AjaxLink alink = new AjaxLink("ajaxDelete", item.getModel()) {
                        @Override
                        public void onClick(AjaxRequestTarget target) {
                            //System.out.println("AJAX WORKS");
                            String deleteItem=this.getParent().get("label").getDefaultModelObjectAsString();
                            list.remove(deleteItem);
                            target.add(listContainer);
                        }
                    };
                    Label label = new Label("label", item.getModel());
                item.add(label);
                item.add(alink);
            }
        }; // ei saa aru, miks seda jooksutatakse ajaxiga...

        textField = new TextField<String>("textForm",nimetus);

        //textField.setOutputMarkupId(true);
        form = new Form<Object>("formWicket");


        form.add(new AjaxFormSubmitBehavior(form,"submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                String newItem = nimetus.getObject();
                list.add(newItem);
                nimetus.setObject("");
                target.add(listContainer);
                target.add(form);
            }

            @Override
            protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                super.updateAjaxAttributes(attributes);

                //disable default action
                attributes.setPreventDefault(true);
            }
        });

        listContainer.add(toDoList);

        form.add(textField);
        add(form);
        add(listContainer);

    }
}
