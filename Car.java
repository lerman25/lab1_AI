import java.util.ArrayList;
import java.util.Collections;

public class Car {
 private char key;
 private int x;
 private int y;
 private int length;
 private int lastX;
 private int lastY;
 private boolean hor_orient;
public Car(char _key,int i, int j,int _length,boolean orient)
{
	key=_key;
	y=i;
	x=j;
	length=_length;
	hor_orient=orient;
}
public static ArrayList<Car> getCarsFromBoard(Board _board)
{
	char[][] board = _board.getBoard();
	ArrayList<Character> cars = new ArrayList<Character>();
	ArrayList<Car> boardCars = new ArrayList<Car>();
	for(int i=0;i<_board.size;i++)
	{
		for(int j=0;j<_board.size;j++)
		{
			char current = board[i][j];
			if((current!='.'))
			{
				cars.add(current);
			}
		}
	}
	while(!cars.isEmpty())
	{
		Character current = cars.get(0);
		int size = Collections.frequency(cars,current);
		boardCars.add(new Car(current,-1,-1,size,false));
		do {
				cars.remove(current);
			} while (cars.contains(current));
	}
	for(int i=0;i<_board.size;i++)
	{
		for(int j=0;j<_board.size;j++)
		{
			char current = board[i][j];
			if(current!='.')
			{
				int index = Car.getIndex(boardCars, current);
				if(index >=0) //exists
				{
					if(boardCars.get(index).getX()==-1) //not been updated yet
					{
						boardCars.get(index).setX(j);
						boardCars.get(index).setY(i);
						if((j+1)<_board.size)
						{
							if(current==board[i][j+1])
								boardCars.get(index).setHor_orient(true);
						}
					}
					else
					{
						boardCars.get(index).setLastX(j);
						boardCars.get(index).setLastY(i);
					}
				}
			}
		}
	}
	return boardCars;
	
}
public static int getIndex(ArrayList<Car> cars,char c)
{
	for(int i=0;i<cars.size();i++)
	{
		if(cars.get(i).getKey()==c)
			return i;
	}
	return -1;
}
public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}
public boolean isHor_orient() {
	return hor_orient;
}
public void setHor_orient(boolean hor_orient) {
	this.hor_orient = hor_orient;
}
public char getKey() {
	return key;
}
public void setKey(char key) {
	this.key = key;
}
public int getLength() {
	return length;
}
public void setLength(int length) {
	this.length = length;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (hor_orient ? 1231 : 1237);
	result = prime * result + key;
	result = prime * result + length;
	result = prime * result + x;
	result = prime * result + y;
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Car other = (Car) obj;
	if (hor_orient != other.hor_orient)
		return false;
	if (key != other.key)
		return false;
	if (length != other.length)
		return false;
	if (x != other.x)
		return false;
	if (y != other.y)
		return false;
	return true;
}
public String toString()
{
	return "Car "+this.getKey()+" X Pos: "+this.getX()+" Y Pos: "+this.getY()+" Size: "+this.getLength()+" Hor Orient: "+this.isHor_orient()+'\n'+"Last X Pos: "+this.getLastX()+" Last Y Pos: "+this.getLastY();
}
public int getLastX() {
	return lastX;
}
public void setLastX(int lastX) {
	this.lastX = lastX;
}
public int getLastY() {
	return lastY;
}
public void setLastY(int lastY) {
	this.lastY = lastY;
}
}