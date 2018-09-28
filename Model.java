import java.util.ArrayList;

class Model
{
	ArrayList<Sprite> sprites;
	int scrollPos = 0;


	Model()
	{
		sprites = new ArrayList<>();
	}

	public void update()
	{
		for (int i = 0; i < sprites.size(); i++)
		{
			Sprite s = sprites.get(i);
			//remove sprites when they get to bottom of screen, like coins
			if (s.y > 800)
				sprites.remove(i);
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
			//create mario
			if (j.getString("name").equals("mario"))
			{
				Mario m = new Mario(j, this);
				sprites.add(m);
			}
			//create the bricks
			if (j.getString("name").equals("brick"))
			{
				Brick b = new Brick(j, this);
				sprites.add(b);
			}
			if (j.getString("name").equals("coinblock"))
			{
				Coinblock c = new Coinblock(j, this);
				sprites.add(c);
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

	//returns the mario sprite so the controller class can get access to him
	Mario getMario()
	{
		for (int i = 0; i < sprites.size(); i++)
		{
			if ( sprites.get(i).name.equals("mario") )
			{
				Sprite s = sprites.get(i);
				Mario m = (Mario) s;
				return m;
			}
		}
		System.out.println("There's no mario!!!!!!");
		System.exit(1);
		return null;
	}

}
