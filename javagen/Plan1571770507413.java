public class Plan1571770507413 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("A");

}

StartServer("B");

DecreaseDimmer("A");

} else {
for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("A");

}

}

}


}
}
