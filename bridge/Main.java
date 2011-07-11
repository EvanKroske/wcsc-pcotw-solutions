import java.io.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Deque;
import java.util.Collections;

class Main {
	private PrintStream o;
	private BufferedReader i;

	//invariant: times is a sorted deque of crossing times
	private int cross (Deque<Integer> times, List<String> steps) {
		int l = times.size();
		if (l >= 4) {
			int a, b, c, d;
			a = times.pollFirst();
			b = times.pollFirst();
			d = times.pollLast();
			c = times.pollLast();
			if (2 * b <= a + c) {
				times.addFirst(b);
				times.addFirst(a);

				steps.add(String.format("%d %d", a, b));
				steps.add(String.format("%d", a));
				steps.add(String.format("%d %d", c, d));
				steps.add(String.format("%d", b));
				int time = 2 * b + a + d;
				int remainingTime = cross(times, steps);
				return time + remainingTime;
			} else {
				times.addFirst(b);
				times.addFirst(a);
				times.addLast(c);

				steps.add(String.format("%d %d", a, d));
				steps.add(String.format("%d", a));
				int time = a + d;
				int remainingTime = cross(times, steps);
				return time + remainingTime;
			}
		} else {
			o.println("Almost done");
			return 6;
		}
	}

	private boolean handleCase () throws IOException {
		LinkedList<Integer> crossingTimes = new LinkedList<Integer>();
		String l;
		i.readLine();
		for (l = i.readLine(); l != null && !l.isEmpty(); l = i.readLine()) {
			crossingTimes.add(new Integer(l.trim()));
		}
		Collections.sort(crossingTimes);
		List<String> steps = new LinkedList<String>();
		int totalTime = cross(crossingTimes, steps);
		o.println(totalTime);
		for (String s : steps) {
			o.println(s);
		}
		o.println();
		if (l != null) {
			return true;
		} else {
			return false;
		}
	}

	public Main() throws IOException {
		i = new BufferedReader(new InputStreamReader(System.in));
		o = System.out;

		i.readLine();
		i.readLine();
		while (handleCase());
	}

	public static void main (String[] args) {
		try { new Main(); } catch (IOException e) {;};
	}
}
