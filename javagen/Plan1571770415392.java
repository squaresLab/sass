public class Plan1571770415392 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

}

for (int i = 0; i < 2 ; i++) {
DecreaseDimmer("B");
}


for (int i = 0; i < 2 ; i++) {
StartServer("C");
}

for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
StartServer("B");

} else {
DecreaseTraffic("A");
StartServer("B");

}

}



for (int i = 0; i < 3 ; i++) {
StartServer("B");
}


}
}
