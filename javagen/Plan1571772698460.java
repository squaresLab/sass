public class Plan1571772698460 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("C");
}

if ( StartServer("A") ) {
StartServer("C");
StartServer("B");

} else {
StartServer("C");
}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}


for (int i = 0; i < 6 ; i++) {
StartServer("B");
}

for (int i = 0; i < 2 ; i++) {
StartServer("C");
}



StartServer("A");


}
}
