package p5a_package;

public class BasicArrayClassMain
{
	public static void main(String[] args) 
	{
		int index, value;
		int arrayCapacity = 10;
		p5_package.IteratorClass iter = new p5_package.IteratorClass(arrayCapacity);
		
		System.out.println("iteratorTest");
		
		for( index = 0; index < (arrayCapacity * 2); index++) 
		{
			value = (int)(index * 10 * Math.random());
			iter.setBeforeCurrent(value);
			iter.runDiagnosticDisplay();
		}
		
		for( index = 0; index < (arrayCapacity * 2); index ++) 
		{
			iter.movePrev();
			iter.runDiagnosticDisplay();
		}
		
		for( index = 0; index < (arrayCapacity * 2); index ++) 
		{
			iter.moveNext();
			iter.runDiagnosticDisplay();
		}
		
		for( index = 0; index < (arrayCapacity * 2); index ++) 
		{
			value = (int)(Math.random() * 10 * index);
			iter.setAfterCurrent(value);
			iter.runDiagnosticDisplay();
		}
		
		for( index = 0; index < (arrayCapacity * 2); index ++) 
		{
			iter.removeAtCurrent();
			iter.runDiagnosticDisplay();
		}
		
		
		p5_package.StackClass stack = new p5_package.StackClass(arrayCapacity);
		
		System.out.println("stackTest");
		
		for( index = 0; index < (arrayCapacity * 2); index++) 
		{
			value = (int)(Math.random() * 10 * index);
			stack.push(value);
			stack.displayStack();
		}
		
		for( index = 0; index < (arrayCapacity * 2); index++) 
		{
			int top = stack.peekTop();
			System.out.println("Top:" + top);
			stack.pop();
			stack.displayStack();
		}
	}
}
