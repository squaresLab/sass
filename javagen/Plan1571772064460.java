public class Plan1571772064460 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
DecreaseDimmer("B");
}

StartServer("B");

StartServer("A");

}


}
}
