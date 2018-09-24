import java.util.ArrayList;

class Model
{
	ArrayList<Sprite> sprites;
	Mario mario;
	int scrollPos = 0;


	Model()
	{
		mario = new Mario(this);
		sprites = new ArrayList<>();
		sprites.add(mario);
	}

	public void update()
	{
		for (int i = 0; i < sprites.size(); i++)
		{
			Sprite s = sprites.get(i);
			s.update();
		}
	}
    //load sprites from json formatted file
	void unmarshall(Json ob)
	{
		sprites.clear();
		Json json_sprites = ob.get("sprites");
		for (int i = 0; i < json_sprites.size(); i++)
		{
			Json j = json_sprites.get(i);
			if (j.get("name").equals("mario"))
			{
				Mario m = new Mario(this);
				sprites.add(m);
			}
			else
			{
				Brick b = new Brick(j);
				sprites.add(b);
			}
		}
	}
	//convert all current sprite locations to Json file
	Json marshall()
	{
		Json ob = Json.newObject();
		Json json_sprites = Json.newList();
		ob.add("sprites", json_sprites);
		for (int i =0 ; i < sprites.size(); i++)
		{
			Sprite s = sprites.get(i);
			System.out.println(s.name);
            json_sprites.add(s.marshall());
		}
		return ob;
	}

	void save(String filename)
	{
		Json ob = marshall();
		ob.save(filename);
	}

	void load(String filename)
	{
		Json loaded = Json.load(filename);
		unmarshall(loaded);
	}

}
