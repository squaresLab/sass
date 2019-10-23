public class Plan1571775037088 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

StartServer("C");


for (int i = 0; i < 4 ; i++) {
StartServer("A");
}


StartServer("B");
StartServer("A");


for (int i = 0; i < 4 ; i++) {
StartServer("C");
}



}
}
