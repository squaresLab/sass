public class Plan1571775176115 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("A");

DecreaseTraffic("A");

}


}
}
