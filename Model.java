import java.util.ArrayList;

class Model
{
	ArrayList<Sprite> sprites;
	int scrollPos = 0;


	Model()
	{
		sprites = new ArrayList<>();
	}

	//copy constructor for AI implementation
	Model(Model copy)
	{
		sprites = new ArrayList<Sprite>();
		for (int i = 0; i < copy.sprites.size(); i++)
		{
			Sprite other = copy.sprites.get(i);
			Sprite clone = other.cloneme(other, this);
			sprites.add(clone);
		}
	}

	enum Action
	{
		run,
		jump,
		run_and_jump,
	}

	void doAction(Action a)
	{
		if (a == Action.run)
			getMario().right();
		if (a == Action.jump)
			getMario().jump();
		if (a == Action.run_and_jump)
			getMario().run_and_jump();
	}
	//creates a copy of the model and trys all the possible actions from doAction
	//assigning a score for each.  the best score is the chosen path of the AI
	double evaluateAction(Action action, int depth)
	{
		int d, k;
		d = 32;
		k = 5;
		// Evaluate the state
		if(depth >= d)
			return getMario().x + 5000 * getMario().coins - 2 * getMario().jumps;

		// Simulate the action
		Model copy = new Model(this); // uses the copy constructor
		copy.doAction(action);
		copy.update(); // advance simulated time

		// Recurse
		if(depth % k != 0)
			return copy.evaluateAction(action, depth + 1);
		else
		{
			double best = copy.evaluateAction(Action.run, depth + 1);
			best = Math.max(best,
					copy.evaluateAction(Action.jump, depth + 1));
			best = Math.max(best,
					copy.evaluateAction(Action.run_and_jump, depth + 1));
			return best;
		}
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
			else if (j.getString("name").equals("brick"))
			{
				Brick b = new Brick(j, this);
				sprites.add(b);
			}
			else if (j.getString("name").equals("coinblock"))
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
