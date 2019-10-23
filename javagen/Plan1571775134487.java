public class Plan1571775134487 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
DecreaseTraffic("A");
DecreaseTraffic("A");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}



for (int i = 0; i < 2 ; i++) {
StartServer("C");
}


DecreaseTraffic("A");
StartServer("B");
StartServer("A");
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

for (int i = 0; i < 3 ; i++) {
StartServer("C");
}







}
}
