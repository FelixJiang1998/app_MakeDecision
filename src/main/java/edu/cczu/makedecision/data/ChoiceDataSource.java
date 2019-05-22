package edu.cczu.makedecision.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChoiceDataSource {
	private static final String PREFKEY_choices = "choices";
	private static final String PREFKEY_title = "title";

	private SharedPreferences choicePrefs;
	private SharedPreferences titlePrefs;
	public ChoiceDataSource(Context context) {
		choicePrefs = context.getSharedPreferences(PREFKEY_choices, Context.MODE_PRIVATE);
		titlePrefs = context.getSharedPreferences(PREFKEY_title, Context.MODE_PRIVATE);
	}
	
	public List<Choice> findAll() {
		List<Choice> choiceList = new ArrayList<Choice>();
		
		for (Map.Entry<String, ?> entry : choicePrefs.getAll().entrySet()) {
			String key = entry.getKey();
			String name = (String) entry.getValue();
			Choice choice = new Choice(key, name);
			choiceList.add(choice);
		}
		
		return choiceList;
	}

	public SharedPreferences getTitlePrefs() {
		return titlePrefs;
	}
	public boolean save(Choice choice) {
		SharedPreferences.Editor editor = choicePrefs.edit();
		editor.putString(choice.getId(), choice.getName());
		editor.commit();
		return true;

	}
	
	public boolean remove(Choice choice) {
		if (choicePrefs.contains(choice.getId())) {
			SharedPreferences.Editor editor = choicePrefs.edit();
			editor.remove(choice.getId());
			editor.commit();
		}
		return true;
	}
}